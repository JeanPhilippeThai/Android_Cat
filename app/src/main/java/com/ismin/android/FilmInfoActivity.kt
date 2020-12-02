package com.ismin.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_film_info.*

class FilmInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_info)

        val film = intent.getSerializableExtra("EXTRA_FILM") as? Film
        film_info_title.text = film?.title
        film_info_author.text = film?.author
        film_info_date.text = film?.date
        // TO DO : film image
        // peut etre essayer
        // film_info_image.setImageResource(getIntent().getStringExtra("EXTRA_IMAGE").toInt())

    }
}