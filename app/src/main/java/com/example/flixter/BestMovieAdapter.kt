package com.example.flixter

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flixter.BestMovie
import com.example.flixter.OnListFragmentInteractionListener
import com.example.flixter.R
import com.example.flixter.R.id

class BestMovieAdapter(
    private val movies: List<BestMovie>,
    private val mListener: OnListFragmentInteractionListener?
)
    : RecyclerView.Adapter<BestMovieAdapter.MovieViewHolder>()
{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_best_movie, parent, false)
        return MovieViewHolder(view)
    }

    inner class MovieViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: BestMovie? = null
        val mMovieTitle: TextView = mView.findViewById<View>(id.movie_name) as TextView
        val mMovieDescription: TextView = mView.findViewById<View>(id.movie_description) as TextView
        val mMovieImage: ImageView = mView.findViewById<View>(id.movie_image) as ImageView


    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        holder.mItem = movie
        holder.mMovieTitle.text = movie.movieName
        holder.mMovieDescription.text = movie.movieDescription

        val baseUrl = "https://image.tmdb.org/t/p/w500/"
        val imageUrl = baseUrl + movie.movieImage
        Glide.with(holder.mView).load(imageUrl).placeholder(R.drawable.ic_launcher_foreground).centerInside().into(holder.mMovieImage)

        holder.mView.setOnClickListener {
            holder.mItem?.let { movie ->
                mListener?.onItemClick(movie)
            }
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}
