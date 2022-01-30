package com.example.haekalmoviecatalogue.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {

    const val NEWEST = "Newest"
    const val OLDEST = "Oldest"
    const val RANDOM = "Random"

    fun getSortedMovieQuery(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movieentities ")
        if (filter == NEWEST) {
            simpleQuery.append("ORDER BY id DESC")
        } else if (filter == OLDEST) {
            simpleQuery.append("ORDER BY id ASC")
        } else if (filter == RANDOM) {
            simpleQuery.append("ORDER BY RANDOM()")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getSortedTvShowQuery(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM tvshowentities ")
        if (filter == NEWEST) {
            simpleQuery.append("ORDER BY id DESC")
        } else if (filter == OLDEST) {
            simpleQuery.append("ORDER BY id ASC")
        } else if (filter == RANDOM) {
            simpleQuery.append("ORDER BY RANDOM()")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}