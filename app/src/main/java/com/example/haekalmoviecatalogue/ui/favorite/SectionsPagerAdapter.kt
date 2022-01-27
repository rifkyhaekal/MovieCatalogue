package com.example.haekalmoviecatalogue.ui.favorite

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.haekalmoviecatalogue.ui.favorite.favoritemovie.FavoriteMovieFragment
import com.example.haekalmoviecatalogue.ui.favorite.favoritetvshow.FavoriteTvShowFragment

class SectionsPagerAdapter(fragment: FavoriteFragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FavoriteMovieFragment()
            1 -> FavoriteTvShowFragment()
            else -> FavoriteMovieFragment()
        }
    }
}