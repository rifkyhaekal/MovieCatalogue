package com.example.haekalmoviecatalogue.data

data class MovieEntity(
    var movieId: String,
    var title: String,
    var genre: String,
    var overview: String,
    var duration: String,
    var userScore: String,
    var releaseDate: String,
    var status: String,
    var imgPoster: Int
)
