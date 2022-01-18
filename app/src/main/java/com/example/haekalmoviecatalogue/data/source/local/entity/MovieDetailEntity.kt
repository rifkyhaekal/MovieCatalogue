package com.example.haekalmoviecatalogue.data.source.local.entity

import com.example.haekalmoviecatalogue.data.source.remote.response.MovieGenresItem

data class MovieDetailEntity(
    var movieId: Int,
    var title: String,
    var genre: List<MovieGenresItem>,
    var overview: String,
    var duration: Int,
    var userScore: Double,
    var releaseDate: String,
    var status: String,
    var imgPoster: String
)

