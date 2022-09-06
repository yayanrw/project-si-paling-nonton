package com.heyproject.core.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.heyproject.core.data.local.entity.MovieEntity
import com.heyproject.core.domain.model.Movie

data class MovieDto(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
) {
    fun toMovie(): Movie {
        return Movie(
            backdropPath = backdropPath,
            id = id,
            overview = overview,
            posterPath = posterPath,
            releaseDate = releaseDate,
            title = title,
            voteAverage = voteAverage,
            isFavorite = false
        )
    }

    fun toMovieEntity(): MovieEntity {
        return MovieEntity(
            id = id,
            title = title,
            overview = overview,
            backdropPath = backdropPath,
            posterPath = posterPath,
            releaseDate = releaseDate,
            voteAverage = voteAverage,
            isFavorite = false
        )
    }
}