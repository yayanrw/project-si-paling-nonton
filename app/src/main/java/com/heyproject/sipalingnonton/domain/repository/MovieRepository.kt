package com.heyproject.sipalingnonton.domain.repository

import com.heyproject.sipalingnonton.core.Resource
import com.heyproject.sipalingnonton.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovies(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}