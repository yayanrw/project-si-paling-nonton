package com.heyproject.core.domain.model

import android.os.Parcelable
import com.heyproject.core.data.local.entity.MovieEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val backdropPath: String,
    val id: Int,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val isFavorite: Boolean
) : Parcelable {
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
