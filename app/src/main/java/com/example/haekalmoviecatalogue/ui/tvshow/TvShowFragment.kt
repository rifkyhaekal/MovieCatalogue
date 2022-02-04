package com.example.haekalmoviecatalogue.ui.tvshow

import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.example.haekalmoviecatalogue.R
import com.example.haekalmoviecatalogue.data.source.local.entity.TvShowEntity
import com.example.haekalmoviecatalogue.databinding.FragmentTvShowBinding
import com.example.haekalmoviecatalogue.utils.SortUtils
import com.example.haekalmoviecatalogue.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {

    private var _fragmentTvShowBinding: FragmentTvShowBinding? = null
    private val fragmentTvShowBinding get() = _fragmentTvShowBinding!!
    private val tvShowViewModel: TvShowViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

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
        getTvShows(SortUtils.NEWEST)
    }

    private fun setPopularTvShows(items: PagedList<TvShowEntity>?) {

        val tvShowAdapter = TvShowAdapter()
        tvShowAdapter.submitList(items)
        tvShowAdapter.notifyDataSetChanged()

        with(fragmentTvShowBinding.rvTvShow) {
            layoutManager = if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                GridLayoutManager(view?.context, 3)
            } else {
                GridLayoutManager(view?.context, 5)
            }
            setHasFixedSize(true)
            adapter = tvShowAdapter
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
        getTvShows(sort)
        item.isChecked = true
        return super.onOptionsItemSelected(item)
    }

    private fun getTvShows(sort: String) {
        tvShowViewModel.getPopularTvShows(sort).observe(viewLifecycleOwner) { popularMovies ->
            if (popularMovies != null) {
                when (popularMovies.status) {
                    Status.LOADING -> showLoading(true)
                    Status.SUCCESS -> {
                        showLoading(false)
                        showTvShowList(true)
                        setPopularTvShows(popularMovies.data)
                    }
                    Status.ERROR -> {
                        showLoading(false)
                        showTvShowList(false)
                    }
                }
            }
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