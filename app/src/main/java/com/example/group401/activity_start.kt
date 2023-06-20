package com.example.group401

import android.graphics.Rect
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.os.Bundle
import android.widget.EditText
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.net.Uri
import com.example.group401.R




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

        val categories = listOf(
            Category("Familie", listOf(
                VideoItem("Video First", R.drawable.thumbfam1, "https://test-videos.co.uk/vids/jellyfish/mp4/h264/1080/Jellyfish_1080_10s_1MB.mp4"),
                VideoItem("Video 2", R.drawable.thumbfam2, "DEEP_LINK_2"),
                VideoItem("Video 3", R.drawable.thumbfam3, "DEEP_LINK_3"),
                VideoItem("Video 2", R.drawable.thumbfam4, "DEEP_LINK_4"),
                VideoItem("Video 3", R.drawable.thumbfam5, "DEEP_LINK_5")
            )),
            Category("Work", listOf(
                VideoItem("Video A", R.drawable.thumbwork1, "DEEP_LINK_A"),
                VideoItem("Video B", R.drawable.thumbwork2, "DEEP_LINK_B"),
                VideoItem("Video C", R.drawable.thumbwork3, "DEEP_LINK_C"),
                VideoItem("Video B", R.drawable.thumbwork4, "DEEP_LINK_D"),
                VideoItem("Video C", R.drawable.thumbwork5, "DEEP_LINK_E"),

            )),
            Category("Beauty", listOf(
                VideoItem("Video A", R.drawable.thumbwork4, "DEEP_LINK_A"),
                VideoItem("Video B", R.drawable.thumbwork5, "DEEP_LINK_B"),
                VideoItem("Video C", R.drawable.thumbwork2, "DEEP_LINK_C"),
                VideoItem("Video B", R.drawable.thumbwork1, "DEEP_LINK_D"),
                VideoItem("Video C", R.drawable.thumbwork3, "DEEP_LINK_E")
            )),
        )


        for (category in categories) {
            category.createView(layoutInflater, parentLayout)
        }

        menuButton = findViewById<Button>(R.id.menuButton)
        menuButton.setOnClickListener {
            if (popupWindow.isShowing) {
                popupWindow.dismiss()
            } else {
                showPopupMenu()
            }
        }

        // Initialisiere das Popup-Fenster
        popupWindow = PopupWindow(this)

        // Initialisiere das Suchfeld und den "S"-Button
        searchField = findViewById<EditText>(R.id.searchField)
        searchField.visibility = View.GONE // Ausblenden des Suchfelds zu Beginn
        searchButton = findViewById<Button>(R.id.searchButton)
        searchButton.setOnClickListener {
            toggleSearchVisibility()
            val searchText = searchField.text.toString()
            if (searchText.isNotEmpty()) {

                showToast("Start search")
            }
        }

    }


    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        // Überprüfe, ob der Benutzer außerhalb des Popup-Fensters geklickt hat und schließe es
        if (event.action == MotionEvent.ACTION_DOWN && popupWindow.isShowing) {
            val contentView = popupWindow.contentView
            val rect =  Rect()
            contentView.getGlobalVisibleRect(rect)
            if (!rect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                popupWindow.dismiss() // Schließe das Popup-Fenster
                return true
            }
        }
        return super.dispatchTouchEvent(event)
    }
    private fun showPopupMenu() {
        // Erzeuge Popup-Fenster für das Menü
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val menuView = inflater.inflate(R.layout.menu_layout, null)

        // Initialisiere das Popup-Fenster
        popupWindow = PopupWindow(menuView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        // Konfiguriere die Buttons im Menü
        val profileButton = menuView.findViewById<Button>(R.id.profileButton)
        val settingsButton = menuView.findViewById<Button>(R.id.settingsButton)
        val logoutButton = menuView.findViewById<Button>(R.id.logoutButton)

        // Setze die Klicklistener für die Buttons
        profileButton.setOnClickListener {
            // Aktion für den "Profile" Button
            popupWindow.dismiss()  // Schließe das Popup-Fenster
            // Hier kannst du den Code für die Profilaktion hinzufügen
        }

        settingsButton.setOnClickListener {
            // Aktion für den "Settings" Button
            popupWindow.dismiss()  // Schließe das Popup-Fenster
            // Hier kannst du den Code für die Einstellungsaktion hinzufügen
        }

        logoutButton.setOnClickListener {
            // Aktion für den "Logout" Button
            popupWindow.dismiss()  // Schließe das Popup-Fenster
            // Hier kannst du den Code für die Logout-Aktion hinzufügen
        }

        // Zeige das Popup-Fenster an der oberen rechten Ecke des Menü-Buttons an
        val location = IntArray(2)
        menuButton.getLocationOnScreen(location)
        val x = location[0] + menuButton.width
        val y = location[1] - menuButton.height
        popupWindow.showAtLocation(menuButton, Gravity.NO_GRAVITY, x, y)
    }

    private fun toggleSearchVisibility() {
        isSearchVisible = !isSearchVisible
        searchField.visibility = if (isSearchVisible) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

class Category(val name: String, val videoList: List<VideoItem>) {
    fun createView(inflater: LayoutInflater, parentLayout: LinearLayout) {
        val categoryView = inflater.inflate(R.layout.category_layout, null)
        val categoryNameTextView = categoryView.findViewById<TextView>(R.id.categoryName)
        val videoLayout = categoryView.findViewById<LinearLayout>(R.id.videoLayout)
        val scrollView = categoryView.findViewById<HorizontalScrollView>(R.id.scrollView)

        categoryNameTextView.text = name

        // Erstelle dynamisch Video-Views
        for (video in videoList) {
            val videoView = inflater.inflate(R.layout.item_video, null)
            val thumbnailImageView = videoView.findViewById<ImageView>(R.id.thumbnailImageView)
            val titleTextView = videoView.findViewById<TextView>(R.id.titleTextView)

            thumbnailImageView.setImageResource(video.thumbnail)
            titleTextView.text = video.title

            videoView.setOnClickListener {
                val intent = Intent(videoView.context, VideoPlayerActivity::class.java).apply {
                    putExtra(VideoPlayerActivity.EXTRA_VIDEO_TITLE, video.title)
                   // putExtra(VideoPlayerActivity.EXTRA_VIDEO_THUMBNAIL, video.thumbnail)
                    putExtra(VideoPlayerActivity.EXTRA_VIDEO_DEEP_LINK, video.deepLink)
                }
                videoView.context.startActivity(intent)
            }



            videoLayout.addView(videoView)
        }

        // Scrollen nach rechts bei Berührung des Bildschirmendes
        scrollView.post {
            scrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT)
        }

        parentLayout.addView(categoryView)    }
}

data class VideoItem(val title: String, val thumbnail: Int, val deepLink: String)
