package com.example.haekalmoviecatalogue.ui.detail.moviedetail

import android.os.Bundle
import android.view.RoundedCorner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.haekalmoviecatalogue.R
import com.example.haekalmoviecatalogue.data.MovieEntity
import com.example.haekalmoviecatalogue.databinding.ActivityDetailMovieBinding
import com.example.haekalmoviecatalogue.databinding.ContentDetailMovieBinding
import net.scarlettsystems.android.glide.Shadow

class DetailMovieActivity : AppCompatActivity() {

    lateinit var contentDetailMovieBinding: ContentDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        contentDetailMovieBinding = activityDetailMovieBinding.detailContent

        setContentView(activityDetailMovieBinding.root)

        setSupportActionBar(activityDetailMovieBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 25f

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailMovieViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getString(EXTRA_MOVIE)
            if (movieId != null) {
                viewModel.setSelectedMovie(movieId)
                populateMovie(viewModel.getMovie() as MovieEntity)
            }
        }
    }

    private fun populateMovie(movieEntity: MovieEntity) {
        contentDetailMovieBinding.textTitleMovie.text = movieEntity.title
        contentDetailMovieBinding.textDurationMovie.text = movieEntity.duration
        contentDetailMovieBinding.textGenreMovie.text = movieEntity.genre
        contentDetailMovieBinding.textOverviewMovie.text = movieEntity.overview
        contentDetailMovieBinding.textReleaseDateMovie.text = movieEntity.releaseDate
        contentDetailMovieBinding.textScoreMovie.text = movieEntity.userScore
        contentDetailMovieBinding.textStatusMovie.text = movieEntity.status

        Glide.with(this)
            .load(movieEntity.imgPoster)
            .transform(CenterCrop(), RoundedCorners(50))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .error(R.drawable.ic_error)
            .into(contentDetailMovieBinding.imgPosterMovie)
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }
}