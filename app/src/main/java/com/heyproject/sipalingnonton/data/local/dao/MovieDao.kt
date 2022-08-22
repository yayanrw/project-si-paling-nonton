package com.heyproject.sipalingnonton.data.local.dao

import androidx.room.*
import com.heyproject.sipalingnonton.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    suspend fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE is_favorite = 1")
    suspend fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE is_trending = 1")
    suspend fun getTrendingMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE is_now_playing = 1")
    suspend fun getNowPlayingMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE is_top_rated = 1")
    suspend fun getTopRatedMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE title LIKE '%' || :search || '%'")
    suspend fun searchMovies(search: String): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: List<MovieEntity>)

    @Update
    suspend fun updateFavoriteMovie(movie: MovieEntity)

    @Query("DELETE FROM movies WHERE created_at <= (strftime('%s','now', '-30 day') * 1000)")
    suspend fun deleteMovies()
}