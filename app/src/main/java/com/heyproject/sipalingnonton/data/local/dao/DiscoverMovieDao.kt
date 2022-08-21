package com.heyproject.sipalingnonton.data.local.dao

import androidx.room.*
import com.heyproject.sipalingnonton.data.local.entity.DiscoverMovieEntity

@Dao
interface DiscoverMovieDao {
    @Query("SELECT * FROM discover_movies")
    suspend fun getMovies(): List<DiscoverMovieEntity>

    @Query("SELECT * FROM discover_movies WHERE is_favorite = 1")
    suspend fun getFavoriteMovies(): List<DiscoverMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(discover_movies: List<DiscoverMovieEntity>)

    @Update
    suspend fun updateFavoriteMovie(discover_movie: DiscoverMovieEntity)
}