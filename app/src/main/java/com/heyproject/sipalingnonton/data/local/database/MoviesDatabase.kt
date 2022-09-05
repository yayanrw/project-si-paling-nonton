package com.heyproject.sipalingnonton.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.heyproject.sipalingnonton.data.local.dao.MovieDao
import com.heyproject.sipalingnonton.data.local.entity.MovieEntity

@Database(
    entities = [
        MovieEntity::class,
    ],
    version = 3,
    exportSchema = false
)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}