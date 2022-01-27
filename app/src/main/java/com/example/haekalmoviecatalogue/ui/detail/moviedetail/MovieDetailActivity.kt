package com.example.haekalmoviecatalogue.ui.detail.moviedetail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.haekalmoviecatalogue.R
import com.example.haekalmoviecatalogue.data.source.local.entity.MovieEntity
import com.example.haekalmoviecatalogue.databinding.ActivityDetailMovieBinding
import com.example.haekalmoviecatalogue.databinding.ContentDetailMovieBinding
import com.example.haekalmoviecatalogue.utils.Common
import com.example.haekalmoviecatalogue.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var activityDetailMovieBinding: ActivityDetailMovieBinding
    private lateinit var contentDetailMovieBinding: ContentDetailMovieBinding

    private val movieDetailViewModel: MovieDetailViewModel by viewModel()
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        contentDetailMovieBinding = activityDetailMovieBinding.detailContent

        setContentView(activityDetailMovieBinding.root)

        setSupportActionBar(activityDetailMovieBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 25f

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getInt(EXTRA_MOVIE)

            showDetailMovie(false)
            movieDetailViewModel.setSelectedMovie(movieId)
            movieDetailViewModel.movie.observe(this) { movieDetail ->
                if (movieDetail != null) {
                    when (movieDetail.status) {
                        Status.LOADING -> showLoading(true)
                        Status.SUCCESS -> if (movieDetail.data != null) {
                            showLoading(false)
                            showDetailMovie(true)
                            populateMovie(movieDetail.data)
                        }
                        Status.ERROR -> {
                            showLoading(false)
                            Toast.makeText(applicationContext, "Something wrong", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun populateMovie(movieEntity: MovieEntity?) {
        contentDetailMovieBinding.apply {
            if (movieEntity != null) {
                textTitleMovie.text = movieEntity.title
                textDurationMovie.text = movieEntity.duration
                textGenreMovie.text = movieEntity.genre
                textOverviewMovie.text = if (movieEntity.overview != "") movieEntity.overview else "-"
                textReleaseDateMovie.text = movieEntity.releaseDate
                ratingMovie.rating = (((movieEntity.userScore)?.toFloat() ?: 2) as Float)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        movieDetailViewModel.movie.observe(this) { movieDetail ->
            if (movieDetail != null) {
                when(movieDetail.status) {
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> if (movieDetail.data != null) {
                        showLoading(false)
                        val state = movieDetail.data.favorite
                        setFavoriteState(state)
                    }
                    Status.ERROR -> {
                        showLoading(false)
                        Toast.makeText(applicationContext, "Something wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            movieDetailViewModel.setFavorite()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_yellow)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_starred_yellow)
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