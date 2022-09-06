package com.heyproject.core.data.local.dao

import androidx.room.*
import com.heyproject.core.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE title LIKE '%' || :search || '%'")
    fun searchMovies(search: String): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies where is_favorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movies: List<MovieEntity>)

    @Query("DELETE FROM movies WHERE created_at <= (strftime('%s','now', '-30 day') * 1000)")
    suspend fun deleteMovies()

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)
}