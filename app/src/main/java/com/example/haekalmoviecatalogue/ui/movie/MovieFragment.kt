package com.example.haekalmoviecatalogue.ui.movie

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
import com.example.haekalmoviecatalogue.data.source.local.entity.MovieItemEntity
import com.example.haekalmoviecatalogue.databinding.FragmentMovieBinding
import com.example.haekalmoviecatalogue.utils.ConnectionLiveData
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private var _fragmentMovieBinding: FragmentMovieBinding? = null
    private val fragmentMovieBinding get() = _fragmentMovieBinding!!
    private val movieViewModel: MovieViewModel by viewModel()
    private lateinit var connectionLiveData: ConnectionLiveData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showErrorInfo(true, getString(R.string.no_connection), R.drawable.no_connection)
        connectionLiveData = movieViewModel.internetConnection()
        connectionLiveData.observe(viewLifecycleOwner, { isNetworkAvailable ->
            showLoading(true)
            showMovieList(false)

            if (isNetworkAvailable) {
                showErrorInfo(false)
            } else {
                showErrorInfo(true, getString(R.string.no_connection), R.drawable.no_connection)
            }
            movieViewModel.getPopularMovies().observe(viewLifecycleOwner, { popularMovies ->
                if (isNetworkAvailable && popularMovies.success) {
                    showLoading(false)
                    showErrorInfo(false)
                    showMovieList(true)
                    setPopularMovies(popularMovies.results)
                } else {
                    showLoading(false)
                    showErrorInfo(true, getString(R.string.no_connection), R.drawable.no_connection)
                    showMovieList(false)
                    setPopularMovies(popularMovies.results)
                }
            })
        })
    }

    private fun setPopularMovies(items: List<MovieItemEntity>?) {

        val movieAdapater = MovieAdapter()
        movieAdapater.setMovies(items)
        movieAdapater.notifyDataSetChanged()

        with(fragmentMovieBinding.rvMovie) {
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                layoutManager = GridLayoutManager(view?.context, 3)
            } else {
                layoutManager = GridLayoutManager(view?.context, 5)
            }
            setHasFixedSize(true)
            adapter = movieAdapater
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) fragmentMovieBinding.progressBar.visibility =
            View.VISIBLE else fragmentMovieBinding.progressBar.visibility = View.GONE
    }

    private fun showMovieList(isVisible: Boolean) {
        if  (isVisible) fragmentMovieBinding.rvMovie.visibility = View.VISIBLE else fragmentMovieBinding.rvMovie.visibility = View.GONE
    }

    private fun showErrorInfo(
        isVisible: Boolean,
        message: String? = null,
        @DrawableRes infoImg: Int? = null
    ) {
        if (isVisible) {
            fragmentMovieBinding.errorInfo.tvInfo.text = message
            Glide.with(this)
                .load(infoImg)
                .error(R.drawable.ic_error)
                .into(fragmentMovieBinding.errorInfo.imgInfo)
            fragmentMovieBinding.errorInfo.root.visibility = View.VISIBLE
        } else {
            fragmentMovieBinding.errorInfo.root.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentMovieBinding = null
    }
}