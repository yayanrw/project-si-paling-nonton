package com.heyproject.sipalingnonton.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.heyproject.sipalingnonton.data.local.entity.GenreEntity

@Dao
interface GenreDao {
    @Query("SELECT * FROM genre")
    suspend fun getGenre(): List<GenreEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenre(genres: List<GenreEntity>)
}