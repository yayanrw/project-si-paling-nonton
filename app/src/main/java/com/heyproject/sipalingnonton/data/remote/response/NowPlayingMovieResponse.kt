package com.heyproject.sipalingnonton.data.remote.response


import com.google.gson.annotations.SerializedName
import com.heyproject.sipalingnonton.data.remote.dto.DatesDto
import com.heyproject.sipalingnonton.data.remote.dto.MovieDto

data class NowPlayingMovieResponse(
    @SerializedName("dates")
    val dates: DatesDto,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)