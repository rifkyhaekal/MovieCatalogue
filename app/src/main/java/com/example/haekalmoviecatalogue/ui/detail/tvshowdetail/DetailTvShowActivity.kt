package com.example.haekalmoviecatalogue.ui.detail.tvshowdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.haekalmoviecatalogue.R
import com.example.haekalmoviecatalogue.data.source.local.entity.TvShowEntity
import com.example.haekalmoviecatalogue.databinding.ActivityDetailTvShowBinding
import com.example.haekalmoviecatalogue.databinding.ContentDetailTvShowBinding

class DetailTvShowActivity : AppCompatActivity() {
    private lateinit var contentDetailTvShowBinding: ContentDetailTvShowBinding

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
                populateMovie(viewModel.getTvShow())
            }
        }
    }

    private fun populateMovie(tvShowEntity: TvShowEntity) {

        contentDetailTvShowBinding.apply {
            textTitleTvshow.text = tvShowEntity.title
            textTypeTvshow.text = tvShowEntity.type
            textGenreTvshow.text = tvShowEntity.genre
            textOverviewTvshow.text = tvShowEntity.overview
            textNetworkTvshow.text = tvShowEntity.network
            textScoreTvshow.text = tvShowEntity.userScore
            textStatusTvshow.text = tvShowEntity.status
        }

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