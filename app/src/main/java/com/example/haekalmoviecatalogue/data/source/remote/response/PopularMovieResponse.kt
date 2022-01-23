package com.example.haekalmoviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PopularMovieResponse(

    @field:SerializedName("results")
    val results: List<MovieItem>
)

data class MovieItem(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("poster_path")
    val posterPath: String
)
