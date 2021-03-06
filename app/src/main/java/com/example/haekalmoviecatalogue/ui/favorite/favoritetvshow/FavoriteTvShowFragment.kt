package com.example.haekalmoviecatalogue.ui.favorite.favoritetvshow

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.example.haekalmoviecatalogue.R
import com.example.haekalmoviecatalogue.data.source.local.entity.TvShowEntity
import com.example.haekalmoviecatalogue.databinding.FragmentFavoriteTvShowBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteTvShowFragment : Fragment() {

    private var _fragmentFavoriteTvShowBinding: FragmentFavoriteTvShowBinding? = null
    private val fragmentFavoriteTvShowBinding get() = _fragmentFavoriteTvShowBinding!!
    private val favoriteTvShowViewModel: FavoriteTvShowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _fragmentFavoriteTvShowBinding = FragmentFavoriteTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentFavoriteTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoading(true)
        showMovieList(false)
        favoriteTvShowViewModel.getFavoriteTvShow().observe(viewLifecycleOwner) { favoriteTvShow ->
            showLoading(false)
            if (favoriteTvShow != null) {
                showMovieList(true)
                showEmpty(false)
                setFavoriteTvShows(favoriteTvShow)
            } else {
                showMovieList(false)
                showEmpty(true, getString(R.string.empty_tv_show))
            }
        }
    }

    private fun setFavoriteTvShows(items: PagedList<TvShowEntity>?) {

        val movieAdapater = FavoriteTvShowAdapter()
        movieAdapater.submitList(items)
        movieAdapater.notifyDataSetChanged()

        if (movieAdapater.itemCount != 0) {
            showEmpty(false)
        } else {
            showEmpty(true, getString(R.string.empty_tv_show))
        }

        with(fragmentFavoriteTvShowBinding.rvTvShowFavorite) {
            layoutManager = if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                GridLayoutManager(view?.context, 3)
            } else {
                GridLayoutManager(view?.context, 5)
            }
            setHasFixedSize(true)
            adapter = movieAdapater
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) fragmentFavoriteTvShowBinding.progressBar.visibility =
            View.VISIBLE else fragmentFavoriteTvShowBinding.progressBar.visibility = View.GONE
    }

    private fun showMovieList(isVisible: Boolean) {
        if (isVisible) fragmentFavoriteTvShowBinding.rvTvShowFavorite.visibility = View.VISIBLE else fragmentFavoriteTvShowBinding.rvTvShowFavorite.visibility = View.GONE
    }

    private fun showEmpty(
        isVisible: Boolean,
        message: String? = null
    ) {
        if (isVisible) {
            fragmentFavoriteTvShowBinding.emptyInfo.text = message
            fragmentFavoriteTvShowBinding.emptyInfo.visibility = View.VISIBLE
        } else {
            fragmentFavoriteTvShowBinding.emptyInfo.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentFavoriteTvShowBinding = null
    }

}