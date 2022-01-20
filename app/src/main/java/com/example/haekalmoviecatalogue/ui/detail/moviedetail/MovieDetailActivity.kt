package com.example.haekalmoviecatalogue.ui.detail.moviedetail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.haekalmoviecatalogue.R
import com.example.haekalmoviecatalogue.data.source.local.entity.ErrorEntity
import com.example.haekalmoviecatalogue.data.source.local.entity.MovieDetailEntity
import com.example.haekalmoviecatalogue.data.source.remote.response.MovieGenresItem
import com.example.haekalmoviecatalogue.databinding.ActivityDetailMovieBinding
import com.example.haekalmoviecatalogue.databinding.ContentDetailMovieBinding
import com.example.haekalmoviecatalogue.utils.Common
import com.example.haekalmoviecatalogue.viewmodel.ViewModelFactory
import kotlin.math.floor

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var contentDetailMovieBinding: ContentDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        contentDetailMovieBinding = activityDetailMovieBinding.detailContent

        setContentView(activityDetailMovieBinding.root)

        setSupportActionBar(activityDetailMovieBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 25f

        val factory = ViewModelFactory.getInstance()
        val movieDetailViewModel = ViewModelProvider(this, factory)[MovieDetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getInt(EXTRA_MOVIE)
            if (movieId != null) {

                showLoading(true)
                showDetailMovie(false)

                movieDetailViewModel.setSelectedMovie(movieId)
                movieDetailViewModel.getMovieDetail().observe(this, { MovieDetail ->
                    showLoading(false)
                    showDetailMovie(true)
                    populateMovie(MovieDetail)
                })
            }
        }
    }

    private fun populateMovie(movieEntity: MovieDetailEntity) {
        contentDetailMovieBinding.apply {
            textTitleMovie.text = movieEntity.title
            textDurationMovie.text = generateMovieDuration(movieEntity.duration)
            textGenreMovie.text = generateGenres(movieEntity.genre)
            textOverviewMovie.text = movieEntity.overview
            textReleaseDateMovie.text = movieEntity.releaseDate
            ratingMovie.rating = (movieEntity.userScore).toFloat() / 2
            ratingValue.text = movieEntity.userScore.toString()
            textStatusMovie.text = movieEntity.status
        }

        Glide.with(this)
            .load(Common.POSTER_URL + movieEntity.imgPoster)
            .transform(CenterCrop(), RoundedCorners(50))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(contentDetailMovieBinding.imgPosterMovie)
    }

    private fun showDetailMovie(isVisible: Boolean) {
        if (isVisible) {
            contentDetailMovieBinding.contentMovie.visibility = View.VISIBLE
        } else {
            contentDetailMovieBinding.contentMovie.visibility = View.GONE
        }
    }

    private fun showErrorInfo (data: ErrorEntity) {
        if (data.visible) {
            contentDetailMovieBinding.errorInfo.tvInfo.text = data.infoText
            Glide.with(this)
                .load(data.infoImg)
                .error(R.drawable.ic_error)
                .into(contentDetailMovieBinding.errorInfo.imgInfo)
            contentDetailMovieBinding.errorInfo.root.visibility = View.VISIBLE
        } else {
            contentDetailMovieBinding.errorInfo.root.visibility = View.GONE
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) contentDetailMovieBinding.progressBar.visibility = View.VISIBLE else contentDetailMovieBinding.progressBar.visibility = View.GONE
    }

    private fun generateMovieDuration(duration: Int): String {
        val hour: Double = floor(duration.toDouble() / 60)
        val minute: Double = duration.toDouble() % 60

        val duration = "${hour.toInt()}h ${minute.toInt()}m"
        return duration
    }

    private fun generateGenres(genresItem: List<MovieGenresItem>): String {
        val builder = StringBuilder()

        genresItem.forEach { genre  ->
            builder.append(genre.name)
            if (genre.name == genresItem.lastOrNull()?.name) {
                builder.append(".")
            } else {
                builder.append(", ")
            }
        }

        return builder.toString()
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }
}