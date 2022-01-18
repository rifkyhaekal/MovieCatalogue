package com.example.haekalmoviecatalogue.data.source.local.entity

import com.example.haekalmoviecatalogue.data.source.remote.response.NetworksItem
import com.example.haekalmoviecatalogue.data.source.remote.response.TvGenresItem

data class TvShowDetailEntity(
    var tvShowId: Int,
    var title: String,
    var overview: String,
    var status: String,
    var type: String,
    var genre: List<TvGenresItem>,
    var network: List<NetworksItem>,
    var userScore: Double,
    var imgPoster: String
)
