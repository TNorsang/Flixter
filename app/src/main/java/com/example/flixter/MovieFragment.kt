package com.example.flixter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

private const val API_KEY = "abe398e8fe2ce2cf7bd6fa715eb62aff"
private const val MOVIE_SEARCH_URL = "https://api.themoviedb.org/3/movie/popular?language=en-US&page=1&api_key=${API_KEY}"

class MovieFragment: Fragment(), OnListFragmentInteractionListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.movie_list) as RecyclerView
        val context = view.context
//        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        updateAdapter(progressBar, recyclerView)
        return view
    }

    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        val queue = Volley.newRequestQueue(context)
        val stringRequest = StringRequest(Request.Method.GET, MOVIE_SEARCH_URL,
            { response ->
                progressBar.hide()
                Log.d("CUSTOM--->", response.toString())

                val obj = JSONObject(response).getJSONArray("results").toString()
                val gson = Gson()
                val arrayType = object: TypeToken<List<Movie>>() {}.type
                val models: List<Movie> = gson.fromJson(obj, arrayType)

                recyclerView.adapter = context?.let { MovieRecyclerViewAdapter(it, models, this@MovieFragment) }
            },
            { error ->
                progressBar.hide()
                Log.e("CUSTOM--->", error.message.toString())
            })
        queue.add(stringRequest)

    }
}
