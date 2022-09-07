package com.heyproject.core.data.repository

import com.heyproject.core.core.Resource
import com.heyproject.core.data.local.LocalDataSource
import com.heyproject.core.data.remote.RemoteDataSource
import com.heyproject.core.data.remote.dto.MovieDto
import com.heyproject.core.data.utils.ApiResponse
import com.heyproject.core.data.utils.AppExecutors
import com.heyproject.core.data.utils.NetworkBoundResource
import com.heyproject.core.domain.model.Movie
import com.heyproject.core.domain.repository.MovieRepository
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
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieDto>>> {
                return remoteDataSource.getMovies()
            }

            override suspend fun saveCallResult(data: List<MovieDto>) {
                localDataSource.insertMovies(data.map { it.toMovieEntity() })
            }
        }.asFlow()

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map { movies ->
            movies.map {
                it.toMovie()
            }
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovie(movie.toMovieEntity(), state)
        }
    }
}