package com.example.haekalmoviecatalogue.ui.detail.tvshowdetail

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
import com.example.haekalmoviecatalogue.data.source.local.entity.TvShowEntity
import com.example.haekalmoviecatalogue.databinding.ActivityDetailTvShowBinding
import com.example.haekalmoviecatalogue.databinding.ContentDetailTvShowBinding
import com.example.haekalmoviecatalogue.utils.Common
import com.example.haekalmoviecatalogue.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowDetailActivity : AppCompatActivity() {

    private lateinit var activityDetailTvShowBinding: ActivityDetailTvShowBinding
    private lateinit var contentDetailTvShowBinding: ContentDetailTvShowBinding

    private val tvShowDetailViewModel: TvShowDetailViewModel by viewModel()
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailTvShowBinding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        contentDetailTvShowBinding = activityDetailTvShowBinding.detailContent

        setContentView(activityDetailTvShowBinding.root)

        setSupportActionBar(activityDetailTvShowBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 25f

        val extras = intent.extras
        if (extras != null) {
            val tvShowId = extras.getInt(EXTRA_TVSHOW)

            showDetailTvShow(false)
            tvShowDetailViewModel.setSelectedTvShow(tvShowId)
            tvShowDetailViewModel.tvShow.observe(this) { tvShowDetail ->
                if (tvShowDetail != null) {
                    when (tvShowDetail.status) {
                        Status.LOADING -> showLoading(true)
                        Status.SUCCESS -> if (tvShowDetail.data != null) {
                            showLoading(false)
                            showDetailTvShow(true)
                            populateTvShow(tvShowDetail.data)
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

    private fun populateTvShow(tvShowEntity: TvShowEntity) {

        contentDetailTvShowBinding.apply {
            textTitleTvshow.text = tvShowEntity.tvShowDetailEntity?.title
            textTypeTvshow.text = tvShowEntity.tvShowDetailEntity?.type
            textGenreTvshow.text = tvShowEntity.tvShowDetailEntity?.genre
            textOverviewTvshow.text = setOverview(tvShowEntity.tvShowDetailEntity?.overview)
            textNetworkTvshow.text = tvShowEntity.tvShowDetailEntity?.network
            ratingTvShow.rating = setRating(tvShowEntity.tvShowDetailEntity?.userScore)
            ratingValueTvshow.text = tvShowEntity.tvShowDetailEntity?.userScore.toString()
            textStatusTvshow.text = tvShowEntity.tvShowDetailEntity?.status
        }

        Glide.with(this)
            .load(Common.POSTER_URL + tvShowEntity.imgPoster)
            .transform(CenterCrop(), RoundedCorners(50))
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(contentDetailTvShowBinding.imgPosterTvshow)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        tvShowDetailViewModel.tvShow.observe(this) { tvShowDetail ->
            if (tvShowDetail != null) {
                when(tvShowDetail.status) {
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> if (tvShowDetail.data != null) {
                        showLoading(false)
                        val state = tvShowDetail.data.favorite
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
            tvShowDetailViewModel.setFavorite()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_starred_yellow)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_yellow)
        }
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

    private fun setRating(rating: Float?): Float {
        val newRating: Float = if (rating != null) {
            rating / 2
        } else {
            0F
        }
        return newRating
    }

    private fun setOverview(overview: String?): String {
        val newOverview: String = if (overview?.length != 0 && overview != null) {
            overview
        } else {
            "-"
        }
        return newOverview
    }

    companion object {
        const val EXTRA_TVSHOW = "extra_TVSHOW"
    }
}