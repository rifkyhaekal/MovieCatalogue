package com.example.haekalmoviecatalogue.data.source.local.entity

data class PopularTvShowEntity(
    val success: Boolean,
    var results: List<TvShowItemEntity>
)

data class TvShowItemEntity(
    val id: Int,
    val posterPath: String
)


