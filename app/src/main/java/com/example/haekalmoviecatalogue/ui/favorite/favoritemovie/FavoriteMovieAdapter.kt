package com.example.haekalmoviecatalogue.ui.favorite.favoritemovie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.haekalmoviecatalogue.R
import com.example.haekalmoviecatalogue.data.source.local.entity.MovieEntity
import com.example.haekalmoviecatalogue.databinding.ItemsMovieBinding
import com.example.haekalmoviecatalogue.ui.detail.moviedetail.MovieDetailActivity
import com.example.haekalmoviecatalogue.utils.Common

class FavoriteMovieAdapter : RecyclerView.Adapter<FavoriteMovieAdapter.FavoriteMovieViewHolder>() {

    private var listFavoriteMovie = ArrayList<MovieEntity>()

    fun setFavoriteMovies(movies: List<MovieEntity>?) {
        if (movies == null) return
        this.listFavoriteMovie.clear()
        this.listFavoriteMovie.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieViewHolder {
        val itemsMovieBinding =
            ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteMovieViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: FavoriteMovieViewHolder, position: Int) {
        val movie = listFavoriteMovie[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listFavoriteMovie.size

    inner class FavoriteMovieViewHolder(private val binding: ItemsMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteMovie: MovieEntity) {
            with(binding) {
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, MovieDetailActivity::class.java)
                    intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, favoriteMovie.movieId)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(Common.POSTER_URL + favoriteMovie.imgPoster)
                    .transform(CenterCrop())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(imgPoster)
            }
        }
    }
}