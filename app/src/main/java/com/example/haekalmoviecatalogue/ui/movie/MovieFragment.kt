package com.example.haekalmoviecatalogue.ui.movie

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.example.haekalmoviecatalogue.data.source.local.entity.MovieEntity
import com.example.haekalmoviecatalogue.databinding.FragmentMovieBinding
import com.example.haekalmoviecatalogue.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private var _fragmentMovieBinding: FragmentMovieBinding? = null
    private val fragmentMovieBinding get() = _fragmentMovieBinding!!
    private val movieViewModel: MovieViewModel by viewModel()

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

        showLoading(true)
        showMovieList(false)
        movieViewModel.getPopularMovies().observe(viewLifecycleOwner) { popularMovies ->
            if (popularMovies != null) {
                when (popularMovies.status) {
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> {
                        showLoading(false)
                        showMovieList(true)
                        setPopularMovies(popularMovies.data)
                    }
                    Status.ERROR -> {
                        showLoading(false)
                        showMovieList(false)
                    }
                }
            }
        }
    }

    private fun setPopularMovies(items: PagedList<MovieEntity>?) {

        val movieAdapater = MovieAdapter()
        movieAdapater.submitList(items)
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

    override fun onDestroy() {
        super.onDestroy()
        _fragmentMovieBinding = null
    }
}