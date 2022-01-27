package com.example.haekalmoviecatalogue.ui.detail.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

import com.example.haekalmoviecatalogue.data.MovieRepository
import com.example.haekalmoviecatalogue.data.source.local.entity.MovieEntity
import com.example.haekalmoviecatalogue.vo.Resource

class MovieDetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val movieId = MutableLiveData<Int>()

    fun setSelectedMovie(movieId: Int) {
        this.movieId.value = movieId
    }

    var movie: LiveData<Resource<MovieEntity>> = Transformations.switchMap(movieId) { mMovieId ->
        movieRepository.getMovieDetail(mMovieId)
    }

    fun setFavorite() {
        val movieResource = movie.value
        if (movieResource != null) {
            val movieDetail = movieResource.data

            if (movieDetail != null) {
                val movieEntity = MovieEntity(movieDetail.movieId, movieDetail.title, movieDetail.genre, movieDetail.overview, movieDetail.duration, movieDetail.userScore, movieDetail.releaseDate, movieDetail.status, movieDetail.imgPoster)
                val newState = !movieEntity.favorite
                movieRepository.setMovieFavorite(movieEntity, newState)
            }
        }
    }
}