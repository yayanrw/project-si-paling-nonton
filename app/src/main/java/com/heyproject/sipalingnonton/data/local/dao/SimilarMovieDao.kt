package com.heyproject.sipalingnonton.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.heyproject.sipalingnonton.data.local.entity.SimilarMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SimilarMovieDao {
    @Query("SELECT * FROM similar_movies WHERE reference_movie_id = :referenceMovieId")
    suspend fun getSimilarMovies(referenceMovieId: Int): Flow<SimilarMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSimilarMovies(similarMovies: List<SimilarMovieEntity>)

    @Query("DELETE FROM similar_movies WHERE created_at <= (strftime('%s','now', '-30 day') * 1000)")
    suspend fun deleteSimilarMovies()
}