package com.example.haekalmoviecatalogue.ui.detail.tvshowdetail

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
import com.example.haekalmoviecatalogue.data.source.local.entity.TvShowDetailEntity
import com.example.haekalmoviecatalogue.data.source.remote.response.NetworksItem
import com.example.haekalmoviecatalogue.data.source.remote.response.TvGenresItem
import com.example.haekalmoviecatalogue.databinding.ActivityDetailTvShowBinding
import com.example.haekalmoviecatalogue.databinding.ContentDetailTvShowBinding
import com.example.haekalmoviecatalogue.utils.Common
import com.example.haekalmoviecatalogue.utils.JsonHelper
import com.example.haekalmoviecatalogue.viewmodel.ViewModelFactory

class TvShowDetailActivity : AppCompatActivity() {
    private lateinit var contentDetailTvShowBinding: ContentDetailTvShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailTvShowBinding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        contentDetailTvShowBinding = activityDetailTvShowBinding.detailContent

        setContentView(activityDetailTvShowBinding.root)

        setSupportActionBar(activityDetailTvShowBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 25f

        val factory = ViewModelFactory.getInstance(JsonHelper())
        val tvShowDetailViewModel = ViewModelProvider(this, factory)[TvShowDetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val tvShowId = extras.getInt(EXTRA_TVSHOW)
            if (tvShowId != null) {

                showLoading(true)
                showLoading(false)

                tvShowDetailViewModel.setSelectedTvShow(tvShowId)
                tvShowDetailViewModel.getTvShowDetail().observe(this, { TvShowDetail ->
                    populateTvShow(TvShowDetail)
                })
            }
        }
    }

    private fun populateTvShow(tvShowEntity: TvShowDetailEntity) {

        contentDetailTvShowBinding.apply {
            textTitleTvshow.text = tvShowEntity.title
            textTypeTvshow.text = tvShowEntity.type
            textGenreTvshow.text = generateGenres(tvShowEntity.genre)
            textOverviewTvshow.text = tvShowEntity.overview
            textNetworkTvshow.text = generateNetworks(tvShowEntity.network)
            ratingTvShow.rating = (tvShowEntity.userScore).toFloat() / 2
            ratingValue.text = tvShowEntity.userScore.toString()
            textStatusTvshow.text = tvShowEntity.status
        }

        Glide.with(this)
            .load(Common.POSTER_URL + tvShowEntity.imgPoster)
            .transform(CenterCrop(), RoundedCorners(50))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(contentDetailTvShowBinding.imgPosterTvshow)
    }

    private fun showDetailTvShow(isVisible: Boolean) {
        if (isVisible) {
            contentDetailTvShowBinding.contentTvShow.visibility = View.VISIBLE
        } else {
            contentDetailTvShowBinding.contentTvShow.visibility = View.GONE
        }
    }

    private fun showErrorInfo (data: ErrorEntity) {
        if (data.visible) {
            contentDetailTvShowBinding.errorInfo.tvInfo.text = data.infoText
            Glide.with(this)
                .load(data.infoImg)
                .error(R.drawable.ic_error)
                .into(contentDetailTvShowBinding.errorInfo.imgInfo)
            contentDetailTvShowBinding.errorInfo.root.visibility = View.VISIBLE
        } else {
            contentDetailTvShowBinding.errorInfo.root.visibility = View.GONE
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) contentDetailTvShowBinding.progressBar.visibility = View.VISIBLE else contentDetailTvShowBinding.progressBar.visibility = View.GONE
    }

    private fun generateNetworks(networksItem: List<NetworksItem>): String {
        val builder = StringBuilder()

        networksItem.forEach { network  ->
            builder.append(network.name)
            if (network.name == networksItem.lastOrNull()?.name) {
                builder.append(".")
            } else {
                builder.append(", ")
            }
        }

        return builder.toString()
    }

    private fun generateGenres(genresItem: List<TvGenresItem>): String {
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
        const val EXTRA_TVSHOW = "extra_TVSHOW"
    }
}