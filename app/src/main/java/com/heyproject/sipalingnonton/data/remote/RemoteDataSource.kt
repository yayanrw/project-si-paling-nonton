package com.heyproject.sipalingnonton.data.remote

import android.util.Log
import com.heyproject.sipalingnonton.data.remote.dto.MovieDto
import com.heyproject.sipalingnonton.data.util.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

private const val TAG = "RemoteDataSource"

class RemoteDataSource(private val movieApi: MovieApi) {
    suspend fun getMovies(): Flow<ApiResponse<List<MovieDto>>> {
        return flow {
            try {
                val response = movieApi.getMovies(
                    page = null,
                    sortBy = null,
                    withOriginalLanguage = null,
                    withGenres = null
                )
                val results = response.results
                if (results.isNotEmpty()) {
                    emit(ApiResponse.Success(results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}