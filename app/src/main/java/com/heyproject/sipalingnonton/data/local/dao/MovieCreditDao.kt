package com.heyproject.sipalingnonton.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.heyproject.sipalingnonton.data.local.entity.MovieCreditEntity

@Dao
interface MovieCreditDao {
    @Query("SELECT * FROM movie_credits WHERE id = :id")
    suspend fun getMovieCredit(id: Int): List<MovieCreditEntity>

    @Insert
    suspend fun insertMovieCredit(movieCredit: MovieCreditEntity)

    @Query("DELETE FROM movie_credits WHERE created_at <= (strftime('%s','now', '-30 day') * 1000)")
    suspend fun deleteMovieCredit()
}