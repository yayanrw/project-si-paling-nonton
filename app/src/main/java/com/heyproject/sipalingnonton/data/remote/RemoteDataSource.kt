package com.heyproject.sipalingnonton.data.remote

import com.heyproject.sipalingnonton.data.remote.dto.MovieDto

private const val TAG = "RemoteDataSource"

class RemoteDataSource(private val movieApi: MovieApi) {
    suspend fun getMovies(): List<MovieDto> = movieApi.getMovies(null, null, null, null).results
}