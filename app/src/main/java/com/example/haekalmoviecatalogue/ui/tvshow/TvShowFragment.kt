package com.example.haekalmoviecatalogue.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.haekalmoviecatalogue.databinding.FragmentTvShowBinding

class TvShowFragment : Fragment() {

    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[TvShowViewModel::class.java]
            val tvShows = viewModel.getTvShows()

            val movieAdapater = TvShowAdapter()
            movieAdapater.setTvShow(tvShows)

            with(fragmentTvShowBinding.rvTvShow) {
                if (resources.configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT) {
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