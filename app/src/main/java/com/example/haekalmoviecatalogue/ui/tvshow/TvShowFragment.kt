package com.example.haekalmoviecatalogue.ui.tvshow

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.haekalmoviecatalogue.data.source.local.entity.TvShowEntity
import com.example.haekalmoviecatalogue.databinding.FragmentTvShowBinding
import com.example.haekalmoviecatalogue.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {

    private var _fragmentTvShowBinding: FragmentTvShowBinding? = null
    private val fragmentTvShowBinding get() = _fragmentTvShowBinding!!
    private val tvShowViewModel: TvShowViewModel by viewModel()

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

        showLoading(true)
        showTvShowList(false)
        tvShowViewModel.getPopularTvShows().observe(viewLifecycleOwner) { popularTvShows ->
            if (popularTvShows != null) {
                when (popularTvShows.status) {
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> {
                        showLoading(false)
                        showTvShowList(true)
                        setPopularTvShows(popularTvShows.data)
                    }
                    Status.ERROR -> {
                        showLoading(false)
                        showTvShowList(false)
                    }
                }
            }
        }
    }

    private fun setPopularTvShows(items: List<TvShowEntity>?) {

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

    override fun onDestroy() {
        super.onDestroy()
        _fragmentTvShowBinding = null
    }
}