package com.example.haekalmoviecatalogue.data.source.local.entity

data class PopularMovieEntity(
    val success: Boolean,
    var results: List<MovieItemEntity>
)

data class MovieItemEntity(
    val id: Int,
    val posterPath: String
)



