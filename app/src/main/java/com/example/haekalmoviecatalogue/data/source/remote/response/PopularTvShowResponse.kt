package com.example.haekalmoviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PopularTvShowResponse(

	@field:SerializedName("results")
	val results: List<TvShowItem>
)

data class TvShowItem(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("poster_path")
	val posterPath: String
)
