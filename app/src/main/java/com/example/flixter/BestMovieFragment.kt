package com.example.flixter

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.flixter.BestMovie
import com.example.flixter.OnListFragmentInteractionListener
import com.example.flixter.R
import com.example.flixter.BestMovieAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray
import org.json.JSONObject

private const val API_KEY = "5e7b3b464eabd6906bc1c9f68bcfccbb"

class BestMovieFragment : Fragment(), OnListFragmentInteractionListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedIstanceState: Bundle?
    ): View? {

        val isLandscape = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        val layoutId = R.layout.fragment_best_movie_list
        val view = inflater.inflate(layoutId, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        updateAdapter(progressBar, recyclerView)
                return view
    }

    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView){
        progressBar.show()

        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = API_KEY

        client[
            "https://api.themoviedb.org/3/movie/now_playing",
            params,
            object : JsonHttpResponseHandler()
            {
                override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON
                ) {
                    progressBar.hide()

                    val resultsArray : JSONArray = json.jsonObject.getJSONArray("results")

                    val gson = Gson()
                    val arrayMovieType = object : TypeToken<List<BestMovie>>() {}.type

                    val models : List<BestMovie> = gson.fromJson(resultsArray.toString(), arrayMovieType)
                    recyclerView.adapter = BestMovieAdapter(models, this@BestMovieFragment)

                    Log.d("ConnectedMovieAPI", "response successful")

                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    errorResponse: String,
                    t: Throwable?
                ) {
                    progressBar.hide()
                    t?.message?.let{
                        Log.e("ConnectedMovieAPI", errorResponse)
                    }
                }
            }
        ]
    }

    override fun onItemClick(item: BestMovie) {
        Toast.makeText(context, "test: " + item.movieName, Toast.LENGTH_LONG).show()
    }
}