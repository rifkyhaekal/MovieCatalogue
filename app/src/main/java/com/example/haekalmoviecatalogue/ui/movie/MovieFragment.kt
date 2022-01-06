package com.example.haekalmoviecatalogue.ui.movie

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.haekalmoviecatalogue.databinding.FragmentMovieBinding

class MovieFragment : Fragment() {

    private lateinit var fragmentMovieBinding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MovieViewModel::class.java]
            val movies = viewModel.getMovies()

            val movieAdapater = MovieAdapter()
            movieAdapater.setMovies(movies)

            with(fragmentMovieBinding.rvMovie) {
                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    layoutManager = GridLayoutManager(view.context, 3)
                } else {
                    layoutManager = GridLayoutManager(view.context, 5)
                }
                setHasFixedSize(true)
                adapter = movieAdapater
            }
        }
    }
}