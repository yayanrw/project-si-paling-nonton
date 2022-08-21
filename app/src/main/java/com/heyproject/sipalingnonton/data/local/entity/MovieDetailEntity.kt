package com.heyproject.sipalingnonton.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.heyproject.sipalingnonton.domain.model.Genre
import com.heyproject.sipalingnonton.domain.model.MovieDetail

@Entity(tableName = "movies_detail")
data class MovieDetailEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

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

    @ColumnInfo(name = "genres")
    val genres: List<Genre>,

    @ColumnInfo(name = "home_page")
    val homepage: String?,

    @ColumnInfo(name = "imdb_id")
    val imdbId: String,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
) {
    fun toMovieDetail(): MovieDetail {
        return MovieDetail(
            backdropPath = backdropPath,
            genres = genres,
            id = id,
            imdbId = imdbId,
            overview = overview,
            posterPath = posterPath,
            releaseDate = releaseDate,
            title = title,
            voteAverage = voteAverage,
            homepage = homepage
        )
    }
}