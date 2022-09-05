package com.heyproject.sipalingnonton.domain.model

import com.heyproject.sipalingnonton.data.local.entity.MovieEntity

data class Movie(
    val backdropPath: String,
    val id: Int,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val isFavorite: Boolean
) {
    fun toMovieEntity(): MovieEntity {
        return MovieEntity(
            id = id,
            title = title,
            overview = overview,
            backdropPath = backdropPath,
            posterPath = posterPath,
            releaseDate = releaseDate,
            voteAverage = voteAverage,
            isFavorite = isFavorite
        )
    }
}
