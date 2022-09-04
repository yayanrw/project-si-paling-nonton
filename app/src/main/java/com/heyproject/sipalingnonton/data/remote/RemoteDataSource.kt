package com.heyproject.sipalingnonton.data.remote

import com.heyproject.sipalingnonton.data.remote.dto.MovieDto
import com.heyproject.sipalingnonton.data.util.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val movieApi: MovieApi) {
    suspend fun getMovies(): Flow<ApiResponse<List<MovieDto>>> {
        return flow {
            try {
                val response = movieApi.getMovies(1, null, null, null)
                if (response.results.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}