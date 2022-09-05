package com.heyproject.sipalingnonton.domain.usecase

import com.heyproject.sipalingnonton.core.Resource
import com.heyproject.sipalingnonton.domain.model.Movie
import com.heyproject.sipalingnonton.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieUsecase {
    fun getMovies(): Flow<Resource<List<Movie>>>
    fun getMovieDetail(movieId: Int): Flow<Resource<MovieDetail>>
}