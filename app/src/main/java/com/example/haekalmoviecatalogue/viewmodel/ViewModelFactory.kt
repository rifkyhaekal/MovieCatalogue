package com.example.haekalmoviecatalogue.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.haekalmoviecatalogue.ui.movie.MovieViewModel
import com.example.haekalmoviecatalogue.ui.tvshow.TvShowViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val mApplication: Application) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(application)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(mApplication) as T
        } else if (modelClass.isAssignableFrom(TvShowViewModel::class.java)) {
            return TvShowViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown view model class: ${modelClass.name}" )
    }
}