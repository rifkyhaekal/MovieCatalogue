package com.example.haekalmoviecatalogue.data.source.local.entity

data class MovieDetailEntity(
    var movieId: Int,
    var title: String,
    var genre: String,
    var overview: String,
    var duration: String,
    var userScore: Double,
    var releaseDate: String,
    var status: String,
    var imgPoster: String
)

