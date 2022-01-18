package com.example.haekalmoviecatalogue.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.haekalmoviecatalogue.R
import com.example.haekalmoviecatalogue.data.source.remote.response.TvShowItem
import com.example.haekalmoviecatalogue.databinding.ItemsTvShowBinding
import com.example.haekalmoviecatalogue.ui.detail.tvshowdetail.TvShowDetailActivity
import com.example.haekalmoviecatalogue.utils.Common

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private var listTvShow = ArrayList<TvShowItem>()

    fun setTvShow(tvShows: List<TvShowItem>?) {
        if (tvShows == null) return
        this.listTvShow.clear()
        this.listTvShow.addAll(tvShows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemsTvShowBinding = ItemsTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemsTvShowBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShows = listTvShow[position]
        holder.bind(tvShows)
    }

    override fun getItemCount(): Int = listTvShow.size

    inner class TvShowViewHolder(private val binding: ItemsTvShowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowItem) {
            with(binding) {
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, TvShowDetailActivity::class.java)
                    intent.putExtra(TvShowDetailActivity.EXTRA_TVSHOW, tvShow.id)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(Common.POSTER_URL + tvShow.posterPath)
                    .transform(CenterCrop())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                        .error(R.drawable.ic_error)
                    .into(imgPoster)
            }
        }
    }
}