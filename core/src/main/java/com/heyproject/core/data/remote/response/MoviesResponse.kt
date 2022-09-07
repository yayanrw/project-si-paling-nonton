package com.heyproject.core.data.remote.response


import com.google.gson.annotations.SerializedName
import com.heyproject.core.data.remote.dto.MovieDto

data class MoviesResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)