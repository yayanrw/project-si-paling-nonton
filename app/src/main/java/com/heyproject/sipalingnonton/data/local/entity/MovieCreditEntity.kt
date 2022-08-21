package com.heyproject.sipalingnonton.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.heyproject.sipalingnonton.domain.model.Cast
import com.heyproject.sipalingnonton.domain.model.MovieCredit

@Entity(tableName = "movie_credits")
data class MovieCreditEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "cast")
    val cast: List<Cast>,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
) {
    fun toMovieCredit(): MovieCredit {
        return MovieCredit(cast = cast, id = id)
    }
}