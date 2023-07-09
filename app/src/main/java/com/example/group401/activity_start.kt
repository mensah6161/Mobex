package com.example.group401

import android.content.Intent
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class activity_start : AppCompatActivity() {
    private lateinit var popupWindow: PopupWindow
    private lateinit var menuButton: Button
    private lateinit var searchField: EditText
    private lateinit var searchButton: Button
    private var isSearchVisible = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val layoutInflater = LayoutInflater.from(this)
        val parentLayout = findViewById<LinearLayout>(R.id.parentLayout)

        //Code Teil damit es nicht Syncron zur Main geschieht
        GlobalScope.launch(Dispatchers.Main) {
            val categories = withContext(Dispatchers.IO) {
                VideoListGenerator.getCategories()
            }

            for (category in categories) {
                category.createView(layoutInflater, parentLayout)
            }
        }
      /* menuButton = findViewById<Button>(R.id.menuButton)
        menuButton.setOnClickListener {
            if (popupWindow.isShowing) {
                popupWindow.dismiss()
            } else {
                showPopupMenu()
            }
        }*/
        // Popup Fenster
        popupWindow = PopupWindow(this)
        // Suchfeld, erscheint nur wenn man auf den S-Button klickt
      /*  searchField = findViewById<EditText>(R.id.searchField)
        searchField.visibility = View.GONE
        searchButton = findViewById<Button>(R.id.searchButton)
        searchButton.setOnClickListener {
            toggleSearchVisibility() //kehrt es einfach um
            val searchText = searchField.text.toString()
            if (searchText.isNotEmpty()) {
                showToast("Start search") //zum Sehen, obs zur richtigen Zeit einsetzt
            }
        }*/
    }
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        //Sobakd Nutzer außerhalb des PopupFensters klickt -> dismiss Fenster
        if (event.action == MotionEvent.ACTION_DOWN && popupWindow.isShowing) {
            val contentView = popupWindow.contentView
            val rect =  Rect()
            contentView.getGlobalVisibleRect(rect)
            //aras
            if (!rect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                popupWindow.dismiss()
                return true
            }
        }
        return super.dispatchTouchEvent(event)
    }
    private fun showPopupMenu() {
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val menuView = inflater.inflate(R.layout.menu_layout, null)

        // Initialisieren des Popup-Fensters
        popupWindow = PopupWindow(menuView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        // Buttons im Menü
        val profileButton = menuView.findViewById<Button>(R.id.profileButton)
        val settingsButton = menuView.findViewById<Button>(R.id.settingsButton)
        val logoutButton = menuView.findViewById<Button>(R.id.logoutButton)
        // Click für Profile Button
        profileButton.setOnClickListener {
            popupWindow.dismiss()
            //evtl. Öffnen neuer Activity
        }
        //Click für Settings Button
        settingsButton.setOnClickListener {
            popupWindow.dismiss()
        }
        //Click für Logout
        logoutButton.setOnClickListener {
            popupWindow.dismiss()
            //mensah: new Activity -"Youre logged out"
        }
        // Popup soll rechts oben vom Menü Button sich öffnen
        val location = IntArray(2)
        menuButton.getLocationOnScreen(location)
        val x = location[0] + menuButton.width;
        val y = location[0] - menuButton.height;
        popupWindow.showAtLocation(menuButton, Gravity.NO_GRAVITY, x, y)
    }
    //aras
    private fun toggleSearchVisibility() {
        isSearchVisible = !isSearchVisible
        searchField.visibility = if (isSearchVisible) View.VISIBLE else View.GONE
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

//aras
class Category(val name: String, val videoList: List<VideoItemKotlin>) {
    fun createView(inflater: LayoutInflater, parentLayout: LinearLayout) {
        val categoryView = inflater.inflate(R.layout.category_layout, null)
        val categoryNameTextView = categoryView.findViewById<TextView>(R.id.categoryName)
        val videoLayout = categoryView.findViewById<LinearLayout>(R.id.videoLayout)
        val scrollView = categoryView.findViewById<HorizontalScrollView>(R.id.scrollView)

        categoryNameTextView.text = name

        // dynamische Video Views: aras
        for (video in videoList) {
            val videoView = inflater.inflate(R.layout.item_video, null)
            val thumbnailImageView = videoView.findViewById<ImageView>(R.id.thumbnailImageView)
            val titleTextView = videoView.findViewById<TextView>(R.id.titleTextView)

            val target = object : CustomTarget<Drawable>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    thumbnailImageView.setImageDrawable(resource)
                    println("Successful thumbnail at: ${video.title} Link thumbnail: ${video.thumbnailUrl}")
                }

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    println("Failed to load thumbnail: ${video.title} Link to missing thumbnail: ${video.thumbnailUrl}")
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    println("Image load cleared")
                }
            }

            Glide.with(videoView)
                .load(video.thumbnailUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(target)

            titleTextView.text = video.title

            videoView.setOnClickListener {
                val intent = Intent(videoView.context, VideoPlayerActivity::class.java).apply {
                    putExtra(VideoPlayerActivity.EXTRA_VIDEO_TITLE, video.title)
                    putExtra(VideoPlayerActivity.EXTRA_VIDEO_DEEP_LINK, video.deepLink)
                    putExtra(VideoPlayerActivity.VIDEO_SIZE, video.video_size)
                    putExtra(VideoPlayerActivity.CREATED, video.created)
                    putExtra(VideoPlayerActivity.INSTITUTION, video.institution)
                    putExtra(VideoPlayerActivity.INSTITUTION_LOGO, video.institution_logo.toString())
                    putExtra(VideoPlayerActivity.PUBLISHER, video.publisher)
                    putExtra(VideoPlayerActivity.CATEGORY, video.category)
                    putExtra(VideoPlayerActivity.SUBCATEGORY, video.subcategory)
                    putExtra(VideoPlayerActivity.AV_FROM, video.available_from)
                    putExtra(VideoPlayerActivity.AV_UNTIL, video.available_to)
                    putExtra(VideoPlayerActivity.CHILD_FRIENDLY, video.child_friendly)
                    putExtra(VideoPlayerActivity.EXTRA_VIDEO_THUMBNAIL, video.thumbnailUrl)

                }
                videoView.context.startActivity(intent)
            }
            videoLayout.addView(videoView)
        }

        // Scroll to the right when touching the right edge of the screen
        scrollView.post {
            scrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT)
        }
        parentLayout.addView(categoryView)
    }
}

//aras
data class VideoItemKotlin(
    val title: String,
    val thumbnailUrl: String,
    val deepLink: String,
    val site_url: String,
    val video_size: Int,
    val created: String,
    val institution: String,
    val institution_logo: String?,
    val publisher: String,
    val duration: Int,
    val category: String?,
    val subcategory: String?,
    val available_from: String,
    val available_to: String,
    val is_child_friendly: Int,
    val child_friendly: Int
) {}