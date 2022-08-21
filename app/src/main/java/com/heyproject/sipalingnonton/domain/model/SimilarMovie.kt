package com.heyproject.sipalingnonton.domain.model

import androidx.room.ColumnInfo

data class SimilarMovie(
    val id: Int,
    val movieId: Int,
    val referenceMovieId: Int,
    val title: String,
    val overview: String,
    val backdropPath: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Double,
    val genreIds: List<Int>,
)
