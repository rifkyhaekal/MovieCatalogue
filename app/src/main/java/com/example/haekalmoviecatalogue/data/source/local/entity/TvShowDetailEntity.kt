package com.example.haekalmoviecatalogue.data.source.local.entity

data class TvShowDetailEntity(
    var tvShowId: Int,
    var title: String,
    var overview: String,
    var status: String,
    var type: String,
    var genre: String,
    var network: String,
    var userScore: Double,
    var imgPoster: String
)
