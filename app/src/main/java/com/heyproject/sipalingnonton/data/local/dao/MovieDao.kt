package com.heyproject.sipalingnonton.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.heyproject.sipalingnonton.data.local.entity.MovieDetailEntity
import com.heyproject.sipalingnonton.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    suspend fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE title LIKE '%' || :search || '%'")
    suspend fun searchMovies(search: String): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: List<MovieEntity>)

    @Query("DELETE FROM movies WHERE created_at <= (strftime('%s','now', '-30 day') * 1000)")
    suspend fun deleteMovies()
}