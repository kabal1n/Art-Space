package com.example.lab2artspace


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var imageTitle: TextView
    private lateinit var imageAuthor: TextView
    private lateinit var btnNext: Button
    private lateinit var btnPrevious: Button

    private var currentIndex = 0
    private val imageList = listOf(
        ImageItem(
            R.drawable.image1,
            "Бурдж-Халифа",
            "Эдриан Смит",
            2010
        ),
        ImageItem(
            R.drawable.image2,
            "Тадж-Махал",
            "Устад Ахмад Лахаури",
            1653
        ),
        ImageItem(
            R.drawable.image3,
            "Замок Нойшванштайн",
            "Эдуард Ридель",
            1886
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        imageTitle = findViewById(R.id.imageTitle)
        imageAuthor = findViewById(R.id.imageAuthor)
        btnNext = findViewById(R.id.btnNext)
        btnPrevious = findViewById(R.id.btnPrevious)

        updateImageInfo()

        btnNext.setOnClickListener {
            if (currentIndex < imageList.lastIndex) {
                currentIndex++
                updateImageInfo()
            }
            updateButtonState()
        }

        btnPrevious.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                updateImageInfo()
            }
            updateButtonState()
        }

        updateButtonState()
    }

    private fun updateImageInfo() {
        val currentImage = imageList[currentIndex]
        imageView.setImageResource(currentImage.imageResId)
        imageTitle.text = currentImage.title
        imageAuthor.text = "${currentImage.author} (${currentImage.year})"
    }

    private fun updateButtonState() {
        btnPrevious.isEnabled = currentIndex > 0
        btnNext.isEnabled = currentIndex < imageList.lastIndex
    }

    data class ImageItem(
        val imageResId: Int,
        val title: String,
        val author: String,
        val year: Int
    )
}