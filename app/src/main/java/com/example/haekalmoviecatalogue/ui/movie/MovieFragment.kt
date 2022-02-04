package com.example.haekalmoviecatalogue.ui.movie

import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.example.haekalmoviecatalogue.R
import com.example.haekalmoviecatalogue.data.source.local.entity.MovieEntity
import com.example.haekalmoviecatalogue.databinding.FragmentMovieBinding
import com.example.haekalmoviecatalogue.utils.SortUtils
import com.example.haekalmoviecatalogue.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private var _fragmentMovieBinding: FragmentMovieBinding? = null
    private val fragmentMovieBinding get() = _fragmentMovieBinding!!
    private val movieViewModel: MovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

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
        getMovise(SortUtils.NEWEST)
    }

    private fun setPopularMovies(items: PagedList<MovieEntity>?) {

        val movieAdapater = MovieAdapter()
        movieAdapater.submitList(items)
        movieAdapater.notifyDataSetChanged()

        with(fragmentMovieBinding.rvMovie) {
            layoutManager = if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                GridLayoutManager(view?.context, 3)
            } else {
                GridLayoutManager(view?.context, 5)
            }
            setHasFixedSize(true)
            adapter = movieAdapater
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var sort = ""
        when (item.itemId) {
            R.id.action_newest -> sort = SortUtils.NEWEST
            R.id.action_oldest -> sort = SortUtils.OLDEST
            R.id.action_random -> sort = SortUtils.RANDOM
        }
        getMovise(sort)
        item.isChecked = true
        return super.onOptionsItemSelected(item)
    }

    private fun getMovise(sort: String) {
        movieViewModel.getPopularMovies(sort).observe(viewLifecycleOwner) { popularMovies ->
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