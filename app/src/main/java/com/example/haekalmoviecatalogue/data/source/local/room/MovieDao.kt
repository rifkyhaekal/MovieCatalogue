package com.example.haekalmoviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.haekalmoviecatalogue.data.source.local.entity.MovieEntity
import com.example.haekalmoviecatalogue.data.source.local.entity.TvShowEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieentities" )
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movieentities WHERE favorite = 1")
    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movieentities WHERE movieId = :movieId")
    fun getMovieDetail(movieId: Int): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Query("UPDATE movieentities SET title = :title, genre = :genre, overview = :overview, duration = :duration, userScore = :userScore, releaseDate = :releaseDate, status = :status WHERE movieId = :movieId")
    fun updateMovieDetail(movieId: Int, title: String, genre: String, overview: String, duration: String, userScore: Float, releaseDate: String, status: String)

    @Query("SELECT * FROM tvshowentities")
    fun getTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tvshowentities WHERE favorite = 1")
    fun getFavoriteTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tvshowentities WHERE tvShowId = :tvShowId")
    fun getTvShowDetail(tvShowId: Int): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(movies: List<TvShowEntity>)

    @Update
    fun updateTvShow(movie: TvShowEntity)

    @Query("UPDATE tvshowentities SET title = :title, overview = :overview, status = :status, type = :type , genre = :genre, network = :network, userScore = :userScore WHERE tvShowId = :tvShowId")
    fun updateTvShowDetail(tvShowId: Int, title: String, overview: String, status: String, type: String, genre: String, network: String, userScore: Float)

}