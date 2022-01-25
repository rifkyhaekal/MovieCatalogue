package com.example.haekalmoviecatalogue.ui.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.haekalmoviecatalogue.ui.movie.MovieFragment
import com.example.haekalmoviecatalogue.ui.tvshow.TvShowFragment

class SectionsPagerAdapter(activity: HomeActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MovieFragment()
            1 -> TvShowFragment()
            else -> TvShowFragment()
        }
    }
}