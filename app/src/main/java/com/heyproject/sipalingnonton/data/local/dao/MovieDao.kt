package com.heyproject.sipalingnonton.data.local.dao

import androidx.room.*
import com.heyproject.sipalingnonton.data.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    suspend fun getMovies(): List<MovieEntity>

    @Query("SELECT * FROM movies WHERE is_favorite = 1")
    suspend fun getFavoriteMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(discover_movies: List<MovieEntity>)

    @Update
    suspend fun updateFavoriteMovie(discover_movie: MovieEntity)
}