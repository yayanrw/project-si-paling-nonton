package com.heyproject.core.data.remote

import com.heyproject.core.data.remote.response.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieApi {
    @GET("discover/movie")
    suspend fun getMovies(
        @Query("page") page: Int?,
        @Query("sortBy") sortBy: String?,
        @Query("withOriginalLanguage") withOriginalLanguage: String?,
        @Query("withGenres") withGenres: String?,
        @Header("Authorization") auth: String
    ): MoviesResponse
}