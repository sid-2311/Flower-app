package com.example.flowers

import Flower

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide

class DetailsScreen : AppCompatActivity() {
    private lateinit var flowerNameTextView: TextView
    private lateinit var flowerDescriptionTextView: TextView
    private lateinit var flowerImageView: ImageView
    private var flower: Flower? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_screen)

        flowerNameTextView = findViewById(R.id.flowerNameTextView)
        flowerDescriptionTextView = findViewById(R.id.flowerDescriptionTextView)
        flowerImageView = findViewById(R.id.flowerImageView)

        // Get the flower data from the intent
        flower = intent.getParcelableExtra("flower") as? Flower

        // Set the flower details
        flower?.let {
            flowerNameTextView.text = it.name
            flowerDescriptionTextView.text = it.description
            Glide.with(this)
                .load(it.imageUrl)
                .into(flowerImageView)
        } ?: run {
            // Handle the case where the flower object is null
            Toast.makeText(this, "Error loading flower details", Toast.LENGTH_SHORT).show()
        }
    }
}