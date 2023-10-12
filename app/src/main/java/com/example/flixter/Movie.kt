package com.example.flixter

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
class Movie (
    @JvmField
    @SerializedName("title")
    var title: String? = null,

    @JvmField
    @SerializedName("overview")
    var overview: String? = null,

    @JvmField
    @SerializedName("poster_path")
    var posterPath: String? = null,

    @JvmField
    @SerializedName("release_date")
    val releaseDate: String? = null,

    @JvmField
    @SerializedName("popularity")
    val popularity: String? = null,
) : java.io.Serializable