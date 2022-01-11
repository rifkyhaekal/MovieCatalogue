package com.example.haekalmoviecatalogue.ui.movie

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.haekalmoviecatalogue.R
import com.example.haekalmoviecatalogue.data.source.local.entity.ErrorEntity
import com.example.haekalmoviecatalogue.data.source.remote.response.MovieItem
import com.example.haekalmoviecatalogue.databinding.FragmentMovieBinding
import com.example.haekalmoviecatalogue.viewmodel.ViewModelFactory

class MovieFragment : Fragment() {

    private var _fragmentMovieBinding: FragmentMovieBinding? = null
    private val fragmentMovieBinding get() = _fragmentMovieBinding!!
    private lateinit var movieViewModel: MovieViewModel

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

        movieViewModel = obtainViewModel(activity as AppCompatActivity)

        movieViewModel.isLoading.observe(viewLifecycleOwner, {
            showLoading(it)
        })

        movieViewModel.showError.observe(viewLifecycleOwner, {
            showErrorInfo(it)
        })

        movieViewModel.popularMovie.observe(viewLifecycleOwner, {
            setPopularMovie(it)
        })

    }

    private fun setPopularMovie(items: List<MovieItem>?) {

        val movieAdapater = MovieAdapter()
        movieAdapater.setMovies(items)

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

    private fun obtainViewModel(activity: AppCompatActivity): MovieViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(MovieViewModel::class.java)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) fragmentMovieBinding.progressBar.visibility = View.VISIBLE else fragmentMovieBinding.progressBar.visibility = View.GONE
    }

    private fun showErrorInfo (data: ErrorEntity) {
        if (data.visible) {
            fragmentMovieBinding.errorInfo.tvInfo.text = data.infoText
            Glide.with(this)
                .load(data.infoImg)
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