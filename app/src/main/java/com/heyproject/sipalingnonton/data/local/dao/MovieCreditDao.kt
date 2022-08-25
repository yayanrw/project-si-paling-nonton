package com.heyproject.sipalingnonton.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.heyproject.sipalingnonton.data.local.entity.MovieCreditEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieCreditDao {
    @Query("SELECT * FROM movie_credits WHERE id = :movieId")
    fun getMovieCredit(movieId: Int): Flow<MovieCreditEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieCredit(movieCredit: MovieCreditEntity)

    @Query("DELETE FROM movie_credits WHERE created_at <= (strftime('%s','now', '-30 day') * 1000)")
    suspend fun deleteMovieCredit()
}