package com.heyproject.sipalingnonton.domain.repository

import com.heyproject.sipalingnonton.core.Resource
import com.heyproject.sipalingnonton.domain.model.*
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<Resource<List<Movie>>>
    fun getMovieDetail(movieId: Int): Flow<Resource<MovieDetail>>
    fun searchMovies(search: String): Flow<Resource<List<Movie>>>
    fun getNowPlayingMovies(): Flow<Resource<List<Movie>>>
    fun getTrendingMovies(): Flow<Resource<List<Movie>>>
    fun getTopRatedMovies(): Flow<Resource<List<Movie>>>
    fun getSimilarMovies(movieId: Int): Flow<Resource<List<Movie>>>
    fun getMovieCredits(movieId: Int): Flow<Resource<MovieCredit>>
    fun getPerson(personId: Int): Flow<Resource<Person>>
    fun getMovieGenre(): Flow<Resource<Genres>>
    fun getFavoriteMovies(): Flow<Resource<List<Movie>>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}