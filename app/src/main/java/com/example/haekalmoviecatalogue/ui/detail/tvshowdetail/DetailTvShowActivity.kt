package com.example.haekalmoviecatalogue.ui.detail.tvshowdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.haekalmoviecatalogue.R
import com.example.haekalmoviecatalogue.data.MovieEntity
import com.example.haekalmoviecatalogue.data.TvShowEntity
import com.example.haekalmoviecatalogue.databinding.ActivityDetailMovieBinding
import com.example.haekalmoviecatalogue.databinding.ActivityDetailTvShowBinding
import com.example.haekalmoviecatalogue.databinding.ContentDetailMovieBinding
import com.example.haekalmoviecatalogue.databinding.ContentDetailTvShowBinding
import com.example.haekalmoviecatalogue.ui.detail.moviedetail.DetailMovieViewModel

class DetailTvShowActivity : AppCompatActivity() {
    lateinit var contentDetailTvShowBinding: ContentDetailTvShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailTvShowBinding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        contentDetailTvShowBinding = activityDetailTvShowBinding.detailContent

        setContentView(activityDetailTvShowBinding.root)

        setSupportActionBar(activityDetailTvShowBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 25f

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailTvShowViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val tvShowId = extras.getString(EXTRA_TVSHOW)
            if (tvShowId != null) {
                viewModel.setSelectedTvShow(tvShowId)
                populateMovie(viewModel.getTvShow() as TvShowEntity)
            }
        }
    }

    private fun populateMovie(tvShowEntity: TvShowEntity) {
        contentDetailTvShowBinding.textTitleTvshow.text = tvShowEntity.title
        contentDetailTvShowBinding.textTypeTvshow.text = tvShowEntity.type
        contentDetailTvShowBinding.textGenreTvshow.text = tvShowEntity.genre
        contentDetailTvShowBinding.textOverviewTvshow.text = tvShowEntity.overview
        contentDetailTvShowBinding.textNetworkTvshow.text = tvShowEntity.network
        contentDetailTvShowBinding.textScoreTvshow.text = tvShowEntity.userScore
        contentDetailTvShowBinding.textStatusTvshow.text = tvShowEntity.status

        Glide.with(this)
            .load(tvShowEntity.imgPoster)
            .transform(CenterCrop(), RoundedCorners(50))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(contentDetailTvShowBinding.imgPosterTvshow)
    }

    companion object {
        const val EXTRA_TVSHOW = "extra_TVSHOW"
    }
}