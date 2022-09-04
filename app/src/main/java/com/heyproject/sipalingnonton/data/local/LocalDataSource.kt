package com.heyproject.sipalingnonton.data.local

import com.heyproject.sipalingnonton.data.local.dao.MovieDao
import com.heyproject.sipalingnonton.data.local.dao.MovieDetailDao
import com.heyproject.sipalingnonton.data.local.entity.MovieDetailEntity
import com.heyproject.sipalingnonton.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val movieDao: MovieDao,
    private val movieDetailDao: MovieDetailDao
) {
    //movieDao
    suspend fun getMovies(): Flow<List<MovieEntity>> = movieDao.getMovies()
    suspend fun searchMovies(search: String): Flow<List<MovieEntity>> =
        movieDao.searchMovies(search)

    suspend fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovie(movies)
    suspend fun deleteMovies() = movieDao.deleteMovies()

    //movieDetailDao
    suspend fun getMovieDetail(movieId: Int): Flow<MovieDetailEntity> =
        movieDetailDao.getMovieDetail(movieId)

    suspend fun insertMovieDetail(movieDetail: MovieDetailEntity) =
        movieDetailDao.insertMovieDetail(movieDetail)

    suspend fun deleteMovieDetails() = movieDetailDao.deleteMovieDetails()
}