package com.heyproject.sipalingnonton.data.repository

import com.heyproject.sipalingnonton.core.Resource
import com.heyproject.sipalingnonton.data.local.LocalDataSource
import com.heyproject.sipalingnonton.data.remote.RemoteDataSource
import com.heyproject.sipalingnonton.data.remote.dto.MovieDto
import com.heyproject.sipalingnonton.data.util.ApiResponse
import com.heyproject.sipalingnonton.data.util.AppExecutors
import com.heyproject.sipalingnonton.data.util.NetworkBoundResource
import com.heyproject.sipalingnonton.domain.model.*
import com.heyproject.sipalingnonton.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val executors: AppExecutors
) : MovieRepository {
    override fun getMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieDto>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getMovies().map { it -> it.map { it.toMovie() } }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieDto>>> {
                return remoteDataSource.getMovies()
            }

            override suspend fun saveCallResult(data: List<MovieDto>) {
                val movies = data.map { it.toMovieEntity() }
                localDataSource.insertMovie(movies)
            }

        }.asFlow()

    override fun getMovieDetail(movieId: Int): Flow<Resource<MovieDetail>> {
        TODO("Not yet implemented")
    }

    override fun searchMovies(search: String): Flow<Resource<List<Movie>>> {
        TODO("Not yet implemented")
    }

    override fun getNowPlayingMovies(): Flow<Resource<List<Movie>>> {
        TODO("Not yet implemented")
    }

    override fun getTrendingMovies(): Flow<Resource<List<Movie>>> {
        TODO("Not yet implemented")
    }

    override fun getTopRatedMovies(): Flow<Resource<List<Movie>>> {
        TODO("Not yet implemented")
    }

    override fun getSimilarMovies(movieId: Int): Flow<Resource<List<Movie>>> {
        TODO("Not yet implemented")
    }

    override fun getMovieCredits(movieId: Int): Flow<Resource<MovieCredit>> {
        TODO("Not yet implemented")
    }

    override fun getPerson(personId: Int): Flow<Resource<Person>> {
        TODO("Not yet implemented")
    }

    override fun getMovieGenre(): Flow<Resource<Genres>> {
        TODO("Not yet implemented")
    }

    override fun getFavoriteMovies(): Flow<Resource<List<Movie>>> {
        TODO("Not yet implemented")
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        TODO("Not yet implemented")
    }
}