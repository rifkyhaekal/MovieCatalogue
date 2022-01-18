package com.example.haekalmoviecatalogue.data.source.local.entity

import com.example.haekalmoviecatalogue.data.source.remote.response.MovieItem

data class PopularMovieEntity(
    var results: List<MovieItem>
)

data class MovieItem(
    val id: Int,
    val posterPath: String
)



