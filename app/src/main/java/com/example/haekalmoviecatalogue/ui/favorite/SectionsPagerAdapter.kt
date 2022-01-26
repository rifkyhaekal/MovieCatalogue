package com.example.haekalmoviecatalogue.ui.favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.haekalmoviecatalogue.ui.movie.MovieFragment
import com.example.haekalmoviecatalogue.ui.tvshow.TvShowFragment

class SectionsPagerAdapter(fragment: FavoriteFragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MovieFragment()
            1 -> TvShowFragment()
            else -> TvShowFragment()
        }
    }
}