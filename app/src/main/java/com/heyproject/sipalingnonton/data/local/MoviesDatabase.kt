package com.heyproject.sipalingnonton.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.heyproject.sipalingnonton.data.local.dao.*
import com.heyproject.sipalingnonton.data.local.entity.*

@Database(
    entities = [
        MovieEntity::class,
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class MoviesDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
}