package com.example.haekalmoviecatalogue.ui.tvshow

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
import com.example.haekalmoviecatalogue.data.source.remote.response.TvShowItem
import com.example.haekalmoviecatalogue.databinding.FragmentTvShowBinding
import com.example.haekalmoviecatalogue.ui.movie.MovieAdapter
import com.example.haekalmoviecatalogue.ui.movie.MovieViewModel
import com.example.haekalmoviecatalogue.viewmodel.ViewModelFactory

class TvShowFragment : Fragment() {

    private var _fragmentTvShowBinding: FragmentTvShowBinding? = null
    private val fragmentTvShowBinding get() = _fragmentTvShowBinding!!
    private lateinit var tvShowViewModel: TvShowViewModel

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

        tvShowViewModel = obtainViewModel(activity as AppCompatActivity)

        tvShowViewModel.isLoading.observe(viewLifecycleOwner, {
            showLoading(it)
        })

        tvShowViewModel.showError.observe(viewLifecycleOwner, {
            showErrorInfo(it)
        })

        tvShowViewModel.popularTvShow.observe(viewLifecycleOwner, {
            setPopularTvShow(it)
        })

    }

    private fun setPopularTvShow(items: List<TvShowItem>?) {

        val tvShowAdapter = TvShowAdapter()
        tvShowAdapter.setTvShow(items)

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

    private fun obtainViewModel(activity: AppCompatActivity): TvShowViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(TvShowViewModel::class.java)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) fragmentTvShowBinding.progressBar.visibility = View.VISIBLE else fragmentTvShowBinding.progressBar.visibility = View.GONE
    }

    private fun showErrorInfo (data: ErrorEntity) {
        if (data.visible) {
            fragmentTvShowBinding.errorInfo.tvInfo.text = data.infoText
            Glide.with(this)
                .load(data.infoImg)
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