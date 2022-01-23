package com.example.haekalmoviecatalogue.ui.detail.tvshowdetail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.haekalmoviecatalogue.R
import com.example.haekalmoviecatalogue.data.source.local.entity.TvShowDetailEntity
import com.example.haekalmoviecatalogue.databinding.ActivityDetailTvShowBinding
import com.example.haekalmoviecatalogue.databinding.ContentDetailTvShowBinding
import com.example.haekalmoviecatalogue.utils.Common
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowDetailActivity : AppCompatActivity() {
    private lateinit var contentDetailTvShowBinding: ContentDetailTvShowBinding
    private val tvShowDetailViewModel: TvShowDetailViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailTvShowBinding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        contentDetailTvShowBinding = activityDetailTvShowBinding.detailContent

        setContentView(activityDetailTvShowBinding.root)

        setSupportActionBar(activityDetailTvShowBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 25f

        val extras = intent.extras
        if (extras != null) {
            val tvShowId = extras.getInt(EXTRA_TVSHOW)
            if (tvShowId != null) {
                showLoading(true)
                showDetailTvShow(false)
                tvShowDetailViewModel.setSelectedTvShow(tvShowId)
                tvShowDetailViewModel.getTvShowDetail().observe(this, { TvShowDetail ->
                    showLoading(false)
                    showDetailTvShow(true)
                    populateTvShow(TvShowDetail)
                })
            }
        }
    }

    private fun populateTvShow(tvShowEntity: TvShowDetailEntity) {

        contentDetailTvShowBinding.apply {
            textTitleTvshow.text = tvShowEntity.title
            textTypeTvshow.text = tvShowEntity.type
            textGenreTvshow.text = tvShowEntity.genre
            textOverviewTvshow.text = if (tvShowEntity.overview != "") tvShowEntity.overview else "-"
            textNetworkTvshow.text = tvShowEntity.network
            ratingTvShow.rating = (tvShowEntity.userScore).toFloat() / 2
            ratingValueTvshow.text = tvShowEntity.userScore.toString()
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
            contentDetailTvShowBinding.imgPosterTvshow.visibility = View.VISIBLE
            contentDetailTvShowBinding.textTitleTvshow.visibility = View.VISIBLE
            contentDetailTvShowBinding.textGenreTvshow.visibility = View.VISIBLE
            contentDetailTvShowBinding.textTypeTvshow.visibility = View.VISIBLE
            contentDetailTvShowBinding.ratingTvShow.visibility = View.VISIBLE
            contentDetailTvShowBinding.ratingValueTvshow.visibility = View.VISIBLE
            contentDetailTvShowBinding.textStatusTvshow.visibility = View.VISIBLE
            contentDetailTvShowBinding.textNetworkTvshow.visibility = View.VISIBLE
            contentDetailTvShowBinding.textOverviewTvshow.visibility = View.VISIBLE
            contentDetailTvShowBinding.txtGenreTvshow.visibility = View.VISIBLE
            contentDetailTvShowBinding.txtTypeTvshow.visibility = View.VISIBLE
            contentDetailTvShowBinding.txtScoreTvshow.visibility = View.VISIBLE
            contentDetailTvShowBinding.txtStatusTvshow.visibility = View.VISIBLE
            contentDetailTvShowBinding.txtNetworkTvshow.visibility = View.VISIBLE
            contentDetailTvShowBinding.txtOverviewTvshow.visibility = View.VISIBLE
        } else {
            contentDetailTvShowBinding.imgPosterTvshow.visibility = View.GONE
            contentDetailTvShowBinding.textTitleTvshow.visibility = View.GONE
            contentDetailTvShowBinding.textGenreTvshow.visibility = View.GONE
            contentDetailTvShowBinding.textTypeTvshow.visibility = View.GONE
            contentDetailTvShowBinding.ratingTvShow.visibility = View.GONE
            contentDetailTvShowBinding.ratingValueTvshow.visibility = View.GONE
            contentDetailTvShowBinding.textStatusTvshow.visibility = View.GONE
            contentDetailTvShowBinding.textNetworkTvshow.visibility = View.GONE
            contentDetailTvShowBinding.textOverviewTvshow.visibility = View.GONE
            contentDetailTvShowBinding.txtGenreTvshow.visibility = View.GONE
            contentDetailTvShowBinding.txtTypeTvshow.visibility = View.GONE
            contentDetailTvShowBinding.txtScoreTvshow.visibility = View.GONE
            contentDetailTvShowBinding.txtStatusTvshow.visibility = View.GONE
            contentDetailTvShowBinding.txtNetworkTvshow.visibility = View.GONE
            contentDetailTvShowBinding.txtOverviewTvshow.visibility = View.GONE
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) contentDetailTvShowBinding.progressBar.visibility =
            View.VISIBLE else contentDetailTvShowBinding.progressBar.visibility = View.GONE
    }

    companion object {
        const val EXTRA_TVSHOW = "extra_TVSHOW"
    }
}