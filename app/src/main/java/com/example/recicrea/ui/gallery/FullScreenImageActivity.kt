package com.example.recicrea.ui.gallery
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.recicrea.R


class FullScreenImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_image)
        val imageView: ImageView = findViewById(R.id.full_screen_image_view)
        val imageResId = intent.getIntExtra("IMAGE_RES_ID", 0)
        imageView.setImageResource(imageResId)
    }
}
