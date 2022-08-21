package com.heyproject.sipalingnonton.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.heyproject.sipalingnonton.domain.model.SimilarMovie

@Entity(tableName = "similar_movies")
data class SimilarMovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "movie_id")
    val movieId: Int,

    @ColumnInfo(name = "reference_movie_id")
    val referenceMovieId: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,

    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    @ColumnInfo(name = "release_date")
    val releaseDate: String,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,

    @ColumnInfo(name = "genre_ids")
    val genreIds: List<Int>,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
) {
    fun toSimilarMovie(): SimilarMovie {
        return SimilarMovie(
            id = id,
            movieId = movieId,
            referenceMovieId = referenceMovieId,
            title = title,
            overview = overview,
            backdropPath = backdropPath,
            posterPath = posterPath,
            releaseDate = releaseDate,
            voteAverage = voteAverage,
            genreIds = genreIds
        )
    }
}
