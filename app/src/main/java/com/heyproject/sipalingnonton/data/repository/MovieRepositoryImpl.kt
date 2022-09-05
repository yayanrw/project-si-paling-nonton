package com.heyproject.sipalingnonton.data.repository

import com.heyproject.sipalingnonton.core.Resource
import com.heyproject.sipalingnonton.data.local.LocalDataSource
import com.heyproject.sipalingnonton.data.remote.RemoteDataSource
import com.heyproject.sipalingnonton.data.remote.dto.MovieDetailDto
import com.heyproject.sipalingnonton.data.remote.dto.MovieDto
import com.heyproject.sipalingnonton.data.utils.ApiResponse
import com.heyproject.sipalingnonton.data.utils.AppExecutors
import com.heyproject.sipalingnonton.data.utils.NetworkBoundResource
import com.heyproject.sipalingnonton.domain.model.Movie
import com.heyproject.sipalingnonton.domain.model.MovieDetail
import com.heyproject.sipalingnonton.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
) : MovieRepository {
    override fun getMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieDto>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getMovies().map { list ->
                    list.map { it.toMovie() }
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data.isNullOrEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieDto>>> {
                return remoteDataSource.getMovies()
            }

            override suspend fun saveCallResult(data: List<MovieDto>) {
                localDataSource.insertMovies(data.map { it.toMovieEntity() })
            }
        }.asFlow()

    override fun getMovieDetail(movieId: Int): Flow<Resource<MovieDetail>> =
        object : NetworkBoundResource<MovieDetail, MovieDetailDto>() {
            override fun loadFromDB(): Flow<MovieDetail> {
                return localDataSource.getMovieDetail(movieId).map { it.toMovieDetail() }
            }

            override fun shouldFetch(data: MovieDetail?): Boolean {
                return data == null
            }

            override suspend fun createCall(): Flow<ApiResponse<MovieDetailDto>> {
                return remoteDataSource.getMovieDetail(movieId)
            }

            override suspend fun saveCallResult(data: MovieDetailDto) {
                localDataSource.insertMovieDetail(data.toMovieDetailEntity())
            }
        }.asFlow()
}