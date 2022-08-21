package com.heyproject.sipalingnonton.data.local.dao

import androidx.room.*
import com.heyproject.sipalingnonton.data.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    suspend fun getMovies(): List<MovieEntity>

    @Query("SELECT * FROM movies WHERE is_favorite = 1")
    suspend fun getFavoriteMovies(): List<MovieEntity>

    @Query("SELECT * FROM movies WHERE is_trending = 1")
    suspend fun getTrendingMovies(): List<MovieEntity>

    @Query("SELECT * FROM movies WHERE is_now_playing = 1")
    suspend fun getNowPlayingMovies(): List<MovieEntity>

    @Query("SELECT * FROM movies WHERE is_top_rated = 1")
    suspend fun getTopRatedMovies(): List<MovieEntity>

    @Query("SELECT * FROM movies WHERE title LIKE '%' || :search || '%'")
    suspend fun searchMovies(search: String): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: List<MovieEntity>)

    @Update
    suspend fun updateFavoriteMovie(movie: MovieEntity)

    @Query("DELETE FROM movies WHERE created_at <= (strftime('%s','now', '-30 day') * 1000)")
    suspend fun deleteMovies()
}