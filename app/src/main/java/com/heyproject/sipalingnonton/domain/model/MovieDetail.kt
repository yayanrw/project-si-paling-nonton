package com.heyproject.sipalingnonton.domain.model

data class MovieDetail(
    val backdropPath: String,
    val homepage: String?,
    val id: Int,
    val imdbId: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
)
