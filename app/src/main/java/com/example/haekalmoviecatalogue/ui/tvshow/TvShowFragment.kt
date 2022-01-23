package com.example.haekalmoviecatalogue.ui.tvshow

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.haekalmoviecatalogue.R
import com.example.haekalmoviecatalogue.data.source.local.entity.TvShowItemEntity
import com.example.haekalmoviecatalogue.databinding.FragmentTvShowBinding
import com.example.haekalmoviecatalogue.utils.ConnectionLiveData
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {

    private var _fragmentTvShowBinding: FragmentTvShowBinding? = null
    private val fragmentTvShowBinding get() = _fragmentTvShowBinding!!
    private val tvShowViewModel: TvShowViewModel by viewModel()
    private lateinit var connectionLiveData: ConnectionLiveData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showErrorInfo(true, getString(R.string.no_connection), R.drawable.no_connection)
        connectionLiveData = tvShowViewModel.internetConnection()
        connectionLiveData.observe(viewLifecycleOwner, { isNetworkAvailable ->
            showLoading(true)
            showTvShowList(false)

            if (isNetworkAvailable) {
                showErrorInfo(false)
            } else {
                showErrorInfo(true, getString(R.string.no_connection), R.drawable.no_connection)
            }
            tvShowViewModel.getPopularTvShows().observe(viewLifecycleOwner, { popularTvShows ->
                if (isNetworkAvailable && popularTvShows.success) {
                    showLoading(false)
                    showErrorInfo(false)
                    showTvShowList(true)
                    setPopularTvShows(popularTvShows.results)
                } else {
                    showLoading(false)
                    showErrorInfo(true, getString(R.string.no_connection), R.drawable.no_connection)
                    showTvShowList(false)
                    setPopularTvShows(popularTvShows.results)
                }
            })
        })
    }

    private fun setPopularTvShows(items: List<TvShowItemEntity>?) {

        val tvShowAdapter = TvShowAdapter()
        tvShowAdapter.setTvShow(items)
        tvShowAdapter.notifyDataSetChanged()

        with(fragmentTvShowBinding.rvTvShow) {
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                layoutManager = GridLayoutManager(view?.context, 3)
            } else {
                layoutManager = GridLayoutManager(view?.context, 5)
            }
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) fragmentTvShowBinding.progressBar.visibility =
            View.VISIBLE else fragmentTvShowBinding.progressBar.visibility = View.GONE
    }

    private fun showTvShowList(isVisible: Boolean) {
        if  (isVisible) fragmentTvShowBinding.rvTvShow.visibility = View.VISIBLE else fragmentTvShowBinding.rvTvShow.visibility = View.GONE
    }

    private fun showErrorInfo(
        isVisible: Boolean,
        message: String? = null,
        @DrawableRes infoImg: Int? = null
    ) {
        if (isVisible) {
            fragmentTvShowBinding.errorInfo.tvInfo.text = message
            Glide.with(this)
                .load(infoImg)
                .error(R.drawable.ic_error)
                .into(fragmentTvShowBinding.errorInfo.imgInfo)
            fragmentTvShowBinding.errorInfo.root.visibility = View.VISIBLE
        } else {
            fragmentTvShowBinding.errorInfo.root.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentTvShowBinding = null
    }
}