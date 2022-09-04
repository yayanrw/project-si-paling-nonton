package com.heyproject.sipalingnonton.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.heyproject.sipalingnonton.data.local.dao.MovieDao
import com.heyproject.sipalingnonton.data.local.dao.MovieDetailDao
import com.heyproject.sipalingnonton.data.local.entity.MovieDetailEntity
import com.heyproject.sipalingnonton.data.local.entity.MovieEntity

@Database(
    entities = [
        MovieEntity::class,
        MovieDetailEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun movieDetailDao(): MovieDetailDao
}