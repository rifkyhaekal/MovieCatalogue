package com.example.haekalmoviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieentities")
data class MovieEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    var movieId: Int,

    @ColumnInfo(name = "imgPoster")
    var imgPoster: String,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false

) {
    @Embedded
    var movieDetailEntity: MovieDetailEntity? = null
}

