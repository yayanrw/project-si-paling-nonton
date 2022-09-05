package com.heyproject.sipalingnonton.data.local

import com.heyproject.sipalingnonton.data.local.dao.MovieDao
import com.heyproject.sipalingnonton.data.local.entity.MovieEntity
import com.heyproject.sipalingnonton.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val movieDao: MovieDao
) {
    //movieDao
    fun getMovies(): Flow<List<MovieEntity>> = movieDao.getMovies()
    fun searchMovies(search: String): Flow<List<MovieEntity>> =
        movieDao.searchMovies(search)
    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovies()

    suspend fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovie(movies)
    suspend fun deleteMovies() = movieDao.deleteMovies()
    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }
}