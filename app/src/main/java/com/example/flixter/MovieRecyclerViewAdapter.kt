package com.example.flixter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val MOVIE_EXTRA = "MOVIE_EXTRA"

class MovieRecyclerViewAdapter(
    private val context: Context,
    private val movies: List<Movie>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val mMovieTitle: TextView = itemView.findViewById<View>(R.id.movie_title) as TextView
        val mMoviePoster: ImageView = itemView.findViewById<View>(R.id.movie_poster) as ImageView

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val movie = movies[adapterPosition]

            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(MOVIE_EXTRA, movie)
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieRecyclerViewAdapter.MovieViewHolder, position: Int) {
        val movie = movies[position]

        holder.mMovieTitle.text = movie.title

        Log.e("CUSTOM--->", "url " + movie.posterPath)
        Log.e("CUSTOM--->", "title " + movie.title)

        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500/" + movie.posterPath)
            .centerInside()
            .into(holder.mMoviePoster)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}