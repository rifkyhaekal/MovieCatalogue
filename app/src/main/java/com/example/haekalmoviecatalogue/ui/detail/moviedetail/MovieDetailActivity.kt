package com.example.haekalmoviecatalogue.ui.detail.moviedetail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.haekalmoviecatalogue.R
import com.example.haekalmoviecatalogue.data.source.local.entity.MovieDetailEntity
import com.example.haekalmoviecatalogue.databinding.ActivityDetailMovieBinding
import com.example.haekalmoviecatalogue.databinding.ContentDetailMovieBinding
import com.example.haekalmoviecatalogue.utils.Common
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var contentDetailMovieBinding: ContentDetailMovieBinding
    private val movieDetailViewModel: MovieDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        contentDetailMovieBinding = activityDetailMovieBinding.detailContent

        setContentView(activityDetailMovieBinding.root)

        setSupportActionBar(activityDetailMovieBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 25f

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getInt(EXTRA_MOVIE)
            showLoading(true)
            showDetailMovie(false)
            movieDetailViewModel.setSelectedMovie(movieId)
            movieDetailViewModel.getMovieDetail().observe(this, { movieDetail ->
                showLoading(false)
                showDetailMovie(true)
                populateMovie(movieDetail)
            })
        }
    }

    private fun populateMovie(movieEntity: MovieDetailEntity?) {
        contentDetailMovieBinding.apply {
            if (movieEntity != null) {
                textTitleMovie.text = movieEntity.title
                textDurationMovie.text = movieEntity.duration
                textGenreMovie.text = movieEntity.genre
                textOverviewMovie.text = if (movieEntity.overview != "") movieEntity.overview else "-"
                textReleaseDateMovie.text = movieEntity.releaseDate
                ratingMovie.rating = (movieEntity.userScore).toFloat() / 2
                ratingValueMovie.text = movieEntity.userScore.toString()
                textStatusMovie.text = movieEntity.status
            }
        }

        if (movieEntity != null) {
            Glide.with(this)
                .load(Common.POSTER_URL + movieEntity.imgPoster)
                .transform(CenterCrop(), RoundedCorners(50))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .error(R.drawable.ic_error)
                .into(contentDetailMovieBinding.imgPosterMovie)
        }
    }

    private fun showDetailMovie(isVisible: Boolean) {
        if (isVisible) {
            contentDetailMovieBinding.imgPosterMovie.visibility = View.VISIBLE
            contentDetailMovieBinding.textTitleMovie.visibility = View.VISIBLE
            contentDetailMovieBinding.textGenreMovie.visibility = View.VISIBLE
            contentDetailMovieBinding.textDurationMovie.visibility = View.VISIBLE
            contentDetailMovieBinding.ratingMovie.visibility = View.VISIBLE
            contentDetailMovieBinding.ratingValueMovie.visibility = View.VISIBLE
            contentDetailMovieBinding.textStatusMovie.visibility = View.VISIBLE
            contentDetailMovieBinding.textReleaseDateMovie.visibility = View.VISIBLE
            contentDetailMovieBinding.textOverviewMovie.visibility = View.VISIBLE
            contentDetailMovieBinding.txtDurationMovie.visibility = View.VISIBLE
            contentDetailMovieBinding.txtGenreMovie.visibility = View.VISIBLE
            contentDetailMovieBinding.txtOverviewMovie.visibility = View.VISIBLE
            contentDetailMovieBinding.txtDurationMovie.visibility = View.VISIBLE
            contentDetailMovieBinding.txtStatusMovie.visibility = View.VISIBLE
            contentDetailMovieBinding.txtScoreMovie.visibility = View.VISIBLE
            contentDetailMovieBinding.txtReleaseDateMovie.visibility = View.VISIBLE
        } else {
            contentDetailMovieBinding.imgPosterMovie.visibility = View.GONE
            contentDetailMovieBinding.textTitleMovie.visibility = View.GONE
            contentDetailMovieBinding.textGenreMovie.visibility = View.GONE
            contentDetailMovieBinding.textDurationMovie.visibility = View.GONE
            contentDetailMovieBinding.ratingMovie.visibility = View.GONE
            contentDetailMovieBinding.ratingValueMovie.visibility = View.GONE
            contentDetailMovieBinding.textStatusMovie.visibility = View.GONE
            contentDetailMovieBinding.textReleaseDateMovie.visibility = View.GONE
            contentDetailMovieBinding.textOverviewMovie.visibility = View.GONE
            contentDetailMovieBinding.txtDurationMovie.visibility = View.GONE
            contentDetailMovieBinding.txtGenreMovie.visibility = View.GONE
            contentDetailMovieBinding.txtOverviewMovie.visibility = View.GONE
            contentDetailMovieBinding.txtDurationMovie.visibility = View.GONE
            contentDetailMovieBinding.txtStatusMovie.visibility = View.GONE
            contentDetailMovieBinding.txtScoreMovie.visibility = View.GONE
            contentDetailMovieBinding.txtReleaseDateMovie.visibility = View.GONE
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) contentDetailMovieBinding.progressBar.visibility =
            View.VISIBLE else contentDetailMovieBinding.progressBar.visibility = View.GONE
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }
}