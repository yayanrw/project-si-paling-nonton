package com.heyproject.sipalingnonton.data.local

import com.heyproject.sipalingnonton.data.local.dao.MovieDao
import com.heyproject.sipalingnonton.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val movieDao: MovieDao,
) {
    //movieDao
    fun getMovies(): Flow<List<MovieEntity>> = movieDao.getMovies()
    fun searchMovies(search: String): Flow<List<MovieEntity>> =
        movieDao.searchMovies(search)

    suspend fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovie(movies)
    suspend fun deleteMovies() = movieDao.deleteMovies()
}