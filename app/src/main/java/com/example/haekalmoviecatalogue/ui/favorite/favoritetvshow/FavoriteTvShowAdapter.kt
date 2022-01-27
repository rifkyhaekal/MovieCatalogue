package com.example.haekalmoviecatalogue.ui.favorite.favoritetvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.haekalmoviecatalogue.R
import com.example.haekalmoviecatalogue.data.source.local.entity.TvShowEntity
import com.example.haekalmoviecatalogue.databinding.ItemsTvShowBinding
import com.example.haekalmoviecatalogue.ui.detail.tvshowdetail.TvShowDetailActivity
import com.example.haekalmoviecatalogue.utils.Common

class FavoriteTvShowAdapter : RecyclerView.Adapter<FavoriteTvShowAdapter.FavoriteTvShowViewHolder>() {

    private var listFavoriteTvShow = ArrayList<TvShowEntity>()

    fun setFavoriteTvShows(tvShows: List<TvShowEntity>?) {
        if (tvShows == null) return
        this.listFavoriteTvShow.clear()
        this.listFavoriteTvShow.addAll(tvShows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTvShowViewHolder {
        val itemsTvShowBinding =
            ItemsTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteTvShowViewHolder(itemsTvShowBinding)
    }

    override fun onBindViewHolder(holder: FavoriteTvShowViewHolder, position: Int) {
        val tvShows = listFavoriteTvShow[position]
        holder.bind(tvShows)
    }

    override fun getItemCount(): Int = listFavoriteTvShow.size

    inner class FavoriteTvShowViewHolder(private val binding: ItemsTvShowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteTvShow: TvShowEntity) {
            with(binding) {
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, TvShowDetailActivity::class.java)
                    intent.putExtra(TvShowDetailActivity.EXTRA_TVSHOW, favoriteTvShow.tvShowId)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(Common.POSTER_URL + favoriteTvShow.imgPoster)
                    .transform(CenterCrop())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(imgPoster)
            }
        }
    }
}