package com.heyproject.sipalingnonton.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.heyproject.sipalingnonton.data.local.dao.*
import com.heyproject.sipalingnonton.data.local.entity.*

@Database(
    entities = [
        GenreEntity::class,
        MovieCreditEntity::class,
        MovieDetailEntity::class,
        MovieEntity::class,
        PersonEntity::class,
        SimilarMovieEntity::class,
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class MoviesDatabase : RoomDatabase() {
    abstract val genreDao: GenreDao
    abstract val movieCreditDao: MovieCreditDao
    abstract val movieDao: MovieDao
    abstract val movieDetailDao: MovieDetailDao
    abstract val personDao: PersonDao
    abstract val similarMovieDao: SimilarMovieDao
}