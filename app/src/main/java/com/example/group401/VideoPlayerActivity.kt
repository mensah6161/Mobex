package com.example.group401

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class VideoPlayerActivity : AppCompatActivity() {// Fast alle neu gemacht von<!-- made by Berat Sahintürk-->

    companion object {
        const val EXTRA_VIDEO_TITLE = "extra_video_title"
        const val EXTRA_VIDEO_THUMBNAIL = "extra_video_thumbnail"
        const val EXTRA_VIDEO_DEEP_LINK = "extra_video_deep_link"
        const val VIDEO_SIZE= "extra_video_size"
        const val CREATED="extra_created"
        const val INSTITUTION="extra_institution"
        const val INSTITUTION_LOGO="extra_institution_logo"
        const val PUBLISHER="extra_publisher"
        const val  DURATION="extra_video_duration"
        const val CATEGORY="extra_category"
        const val SUBCATEGORY="extra_subcategory"
        const val AV_FROM="extra_date_from"
        const val AV_UNTIL="extra_date_until"
        const val CHILD_FRIENDLY="extra_child_friendly"



    }

    private lateinit var videoView: VideoView
    private lateinit var videoTitleTextView: TextView
    private lateinit var fullScreenButton: Button
    private  lateinit var description: TextView
    private  lateinit var Institution_Logo:ImageView
    private lateinit var  Publisher_Text:TextView
    private lateinit var Seekbar:SeekBar
    private lateinit var  Text_time:TextView
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private lateinit var share: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.video_player)
        var text_description:String
        var counter:Int
        var counter2:Int
        var text_from:String
        var stop:Boolean=false
        var text_until:String
        text_from="Available from: "
        text_until="Available until: "
        counter2=0


        videoTitleTextView = findViewById(R.id.Title)
        description=findViewById(R.id.Description)
        Institution_Logo=findViewById(R.id.imageView)
        videoTitleTextView.setText(EXTRA_VIDEO_TITLE)
        fullScreenButton = findViewById(R.id.vollbild)
        videoView = findViewById(R.id.videoView2)
        Publisher_Text=findViewById(R.id.Publisher)
        Seekbar=findViewById(R.id.Time)
        Text_time=findViewById(R.id.Text_time)
        share=findViewById(R.id.Share)



        val videoLogoUrl = intent.getStringExtra(INSTITUTION_LOGO)
        val videoDeepLink = intent.getStringExtra(EXTRA_VIDEO_DEEP_LINK)


        val title= intent.getStringExtra(EXTRA_VIDEO_TITLE)
        val thumbnail=intent.getStringExtra(EXTRA_VIDEO_THUMBNAIL)
        val deep_link= intent.getStringExtra(EXTRA_VIDEO_DEEP_LINK)
        val video_size= intent.getStringExtra( VIDEO_SIZE)
        val created=intent.getStringExtra( CREATED)
        val Institution=intent.getStringExtra( INSTITUTION)
        val publisher= intent.getStringExtra( PUBLISHER)
        val duration= intent.getStringExtra( DURATION)?.toInt() ?: 0  //https://stackoverflow.com/questions/56971571/kotlin-string-to-int-or-zero-default-value
        val category=intent.getStringExtra( CATEGORY)
        val subcategory=intent.getStringExtra( SUBCATEGORY)
        val av_from=intent.getStringExtra( AV_FROM)
        val av_until=intent.getStringExtra( AV_UNTIL)
        val child= intent.getStringExtra( CHILD_FRIENDLY)




        val target = object : CustomTarget<Drawable>() {
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                Institution_Logo.setImageDrawable(resource)
                println("Successfully loaded the Image into the logo location")
                println("")
            }
            override fun onLoadFailed(errorDrawable: Drawable?) {
                println("Failed to load the Image into the logo location And this is the Link$INSTITUTION_LOGO")
            }
            override fun onLoadCleared(placeholder: Drawable?) {
                println("Successfully started loading the Image for the logo")
            }
        }
        startVideoProgressUpdates(Text_time,Seekbar)
        Glide.with(videoView)
            .load(videoLogoUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .centerCrop()
            .into(target)

        text_description="#$category, #$subcategory, Duration: $duration seconds \n available from: ${av_from?.take(10)} - ${av_until?.take(10)}"


        GlobalScope.launch {// The Bars shouldnt be visible whole time
            delay(10000)
            Seekbar.visibility=View.INVISIBLE
            Text_time.visibility=View.INVISIBLE

        }

        share.setOnClickListener{
            val intent= Intent(Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_SUBJECT, " Here is a cool Video you should watch")
            intent.putExtra(Intent.EXTRA_TEXT,videoDeepLink)
            startActivity(Intent.createChooser(intent, "Lets share!"))


        }



        videoTitleTextView.text = title
        description.text= text_description
        Publisher_Text.text= Institution+" "
        Seekbar.max=duration

        // Set video URI for the VideoView
        val videoUri = Uri.parse(videoDeepLink)
        videoView.setVideoURI(videoUri)






        videoView.setOnClickListener {
            if(stop==false){
                videoView.pause()



                stop=true
            } else{
                videoView.start()
                stop=false
            }
            Seekbar.visibility=View.VISIBLE
            Text_time.visibility=View.VISIBLE
            GlobalScope.launch {// The Bars shouldnt be visible whole time
                delay(10000)
                Seekbar.visibility=View.INVISIBLE
                Text_time.visibility=View.INVISIBLE

            }




        }
        // Starting video
        videoView.start()

        // Full screen Option
        fullScreenButton.setOnClickListener {
            val intent = Intent(this, FullVersionActivity::class.java)
            intent.putExtra(FullVersionActivity.EXTRA_VIDEO_DEEP_LINK, videoDeepLink)
            intent.putExtra(FullVersionActivity.EXTRA_VIDEO_TITLE, title)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Stop vid and close everything depending on it/recources
        videoView.stopPlayback()
    }
    private fun startVideoProgressUpdates(textTime: TextView, seekBar: SeekBar) { //https://stackoverflow.com/questions/46001792/writing-a-timer-using-handler-in-kotlin
        handler = Handler()
        val Abstandt = 5

        runnable = object : Runnable {
            override fun run() {
                val currentPosition = videoView.currentPosition //https://developer.android.com/reference/android/widget/VideoView
                val minutes = currentPosition / 60000
                val seconds = (currentPosition % 60000) / 1000

                if (Math.abs(seekBar.progress - (currentPosition / 1000)) > Abstandt) { //https://flexiple.com/javascript/javascript-absolute-value/
                    videoView.seekTo(seekBar.progress * 1000)
                } else {
                    seekBar.progress = currentPosition / 1000
                    textTime.text = String.format("%02d:%02d", minutes, seconds)
                }

                handler.postDelayed(this, 1000) // Aktualisierung alle 1 Sekunde
            }
        }
        handler.postDelayed(runnable, 0) // Erste Aktualisierung sofort starten
    }


}
