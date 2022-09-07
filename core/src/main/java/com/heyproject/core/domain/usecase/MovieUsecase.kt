package com.heyproject.core.domain.usecase

import com.heyproject.core.core.Resource
import com.heyproject.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUsecase {
    fun getMovies(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovies(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}