package com.heyproject.sipalingnonton.domain.usecase

import com.heyproject.sipalingnonton.core.Resource
import com.heyproject.sipalingnonton.domain.model.MovieDetail
import com.heyproject.sipalingnonton.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: MovieRepository) : MovieUsecase {
    override fun getMovies() = movieRepository.getMovies()
    override fun getMovieDetail(movieId: Int): Flow<Resource<MovieDetail>> =
        movieRepository.getMovieDetail(movieId)
}