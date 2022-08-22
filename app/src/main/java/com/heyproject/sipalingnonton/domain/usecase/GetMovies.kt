package com.heyproject.sipalingnonton.domain.usecase

import com.heyproject.sipalingnonton.core.Resource
import com.heyproject.sipalingnonton.domain.model.Movie
import com.heyproject.sipalingnonton.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovies (private val movieRepository: MovieRepository) {
    operator fun invoke(): Flow<Resource<List<Movie>>> {
        return movieRepository.getMovies()
    }
}