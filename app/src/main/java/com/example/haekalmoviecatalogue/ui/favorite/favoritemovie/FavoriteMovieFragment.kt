package com.example.haekalmoviecatalogue.ui.favorite.favoritemovie

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.haekalmoviecatalogue.R
import com.example.haekalmoviecatalogue.data.source.local.entity.MovieEntity
import com.example.haekalmoviecatalogue.databinding.FragmentMovieFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteMovieFragment : Fragment() {

    private var _fragmentMovieFavoriteBinding: FragmentMovieFavoriteBinding? = null
    private val fragmentFavoriteBinding get() = _fragmentMovieFavoriteBinding!!
    private val favoriteMovieViewModel: FavoriteMovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _fragmentMovieFavoriteBinding = FragmentMovieFavoriteBinding.inflate(layoutInflater, container, false)
        return fragmentFavoriteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoading(true)
        showMovieList(false)
        showEmpty(true, getString(R.string.empty_movie))
        favoriteMovieViewModel.getFavoriteMovie().observe(viewLifecycleOwner) { favoriteMovie ->
            if (favoriteMovie != null) {
                showMovieList(true)
                showLoading(false)
                setFavoriteMovies(favoriteMovie)
            } else {
                showMovieList(false)
                showLoading(false)
            }
        }
    }

    private fun setFavoriteMovies(items: List<MovieEntity>?) {
        val movieAdapater = FavoriteMovieAdapter()
        movieAdapater.setFavoriteMovies(items)
        movieAdapater.notifyDataSetChanged()

        if (movieAdapater.itemCount != 0) {
            showEmpty(false)
        } else {
            showEmpty(true, getString(R.string.empty_movie))
        }

        with(fragmentFavoriteBinding.rvMovie) {
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
        if (isLoading) fragmentFavoriteBinding.progressBar.visibility =
            View.VISIBLE else fragmentFavoriteBinding.progressBar.visibility = View.GONE
    }

    private fun showMovieList(isVisible: Boolean) {
        if (isVisible) fragmentFavoriteBinding.rvMovie.visibility = View.VISIBLE else fragmentFavoriteBinding.rvMovie.visibility = View.GONE
    }

    private fun showEmpty(
        isVisible: Boolean,
        message: String? = null
    ) {
        if (isVisible) {
            fragmentFavoriteBinding.emptyInfo.text = message
            fragmentFavoriteBinding.emptyInfo.visibility = View.VISIBLE
        } else {
            fragmentFavoriteBinding.emptyInfo.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentMovieFavoriteBinding = null
    }

}