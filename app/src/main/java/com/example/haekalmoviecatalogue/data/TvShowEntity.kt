package com.example.haekalmoviecatalogue.data

data class TvShowEntity(
    var tvShowId: String,
    var title: String,
    var overview: String,
    var status: String,
    var type: String,
    var genre: String,
    var network: String,
    var userScore: String,
    var imgPoster: Int
)
