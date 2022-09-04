package com.heyproject.sipalingnonton.data.repository

import com.heyproject.sipalingnonton.core.Resource
import com.heyproject.sipalingnonton.data.local.LocalDataSource
import com.heyproject.sipalingnonton.data.remote.RemoteDataSource
import com.heyproject.sipalingnonton.domain.model.*
import com.heyproject.sipalingnonton.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException

class MovieRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) : MovieRepository {
    override fun getMovies(): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        val localData = localDataSource.getMovies().map { it.toMovie() }
        emit(Resource.Loading(data = localData))

        try {
            val remoteData = remoteDataSource.getMovies()
            if (remoteData.isNotEmpty()) {
                localDataSource.deleteMovies()
                localDataSource.insertMovies(remoteData.map { it.toMovieEntity() })
            }
        } catch (e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong!",
                data = localData
            ))
        } catch (e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach server, please check your internet connection.",
                data = localData
            ))
        }

        val newLocalData = localDataSource.getMovies().map { it.toMovie() }
        emit(Resource.Success(newLocalData))

    }

    override fun searchMovies(search: String): Flow<Resource<List<Movie>>> {
        TODO("Not yet implemented")
    }

}