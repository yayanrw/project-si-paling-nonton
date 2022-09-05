package com.heyproject.sipalingnonton.domain.usecase

import com.heyproject.sipalingnonton.domain.model.Movie
import com.heyproject.sipalingnonton.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: MovieRepository) : MovieUsecase {
    override fun getMovies() = movieRepository.getMovies()
    override fun getFavoriteMovies(): Flow<List<Movie>> = movieRepository.getFavoriteMovies()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) =
        movieRepository.setFavoriteMovie(movie, state)
}