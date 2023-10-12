package com.example.flixter

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class DetailActivity : AppCompatActivity() {
    private lateinit var mediaImageView: ImageView
    private lateinit var mediaTitleView: TextView
    private lateinit var mediaOverviewView: TextView
    private lateinit var mediaPopularity: TextView
    private lateinit var mediaReleaseDate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        mediaImageView = findViewById(R.id.mediaImage)
        mediaTitleView = findViewById(R.id.mediaTitle)
        mediaOverviewView = findViewById(R.id.mediaOverview)
        mediaPopularity = findViewById(R.id.mediaPopularity)
        mediaReleaseDate = findViewById(R.id.mediaReleaseDate)

        val movie = intent.getSerializableExtra(MOVIE_EXTRA) as Movie

        mediaTitleView.text = movie.title
        mediaOverviewView.text = movie.overview
        mediaPopularity.text = movie.popularity
        mediaReleaseDate.text = movie.releaseDate

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/" + movie.posterPath)
            .centerInside()
            .into(mediaImageView)
    }
}