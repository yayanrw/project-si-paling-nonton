package com.heyproject.sipalingnonton.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.heyproject.sipalingnonton.data.local.entity.MovieDetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDetailDao {
    @Query("SELECT A.*, B.is_favorite FROM movies_detail A INNER JOIN movies B ON A.id = B.id WHERE A.id = :movieId")
    fun getMovieDetail(movieId: Int): Flow<MovieDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetail(movieDetail: MovieDetailEntity)

    @Query("DELETE FROM movies_detail WHERE created_at <= (strftime('%s','now', '-30 day') * 1000)")
    suspend fun deleteMovieDetails()
}

