package com.heyproject.sipalingnonton.domain.usecase

import com.heyproject.sipalingnonton.core.Resource
import com.heyproject.sipalingnonton.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUsecase {
    fun getMovies(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovies(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}