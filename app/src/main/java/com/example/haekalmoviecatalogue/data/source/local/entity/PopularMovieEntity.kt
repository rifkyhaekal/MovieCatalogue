package com.example.haekalmoviecatalogue.data.source.local.entity

data class PopularMovieEntity(
    var results: List<MovieItemEntity>
)

data class MovieItemEntity(
    val id: Int,
    val posterPath: String
)



