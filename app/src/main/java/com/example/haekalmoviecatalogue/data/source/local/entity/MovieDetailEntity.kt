package com.example.haekalmoviecatalogue.data.source.local.entity

import androidx.room.ColumnInfo

data class MovieDetailEntity(

    @ColumnInfo(name = "title")
    var title: String?,

    @ColumnInfo(name = "genre")
    var genre: String?,

    @ColumnInfo(name = "overview")
    var overview: String?,

    @ColumnInfo(name = "duration")
    var duration: String?,

    @ColumnInfo(name = "userScore")
    var userScore: Float,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String?,

    @ColumnInfo(name = "status")
    var status: String?

)
