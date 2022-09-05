package com.heyproject.sipalingnonton.data.remote

import com.heyproject.sipalingnonton.core.BEARER_TOKEN
import com.heyproject.sipalingnonton.data.remote.response.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieApi {
    @Headers("Authorization: Bearer $BEARER_TOKEN")
    @GET("discover/movie")
    suspend fun getMovies(
        @Query("page") page: Int?,
        @Query("sortBy") sortBy: String?,
        @Query("withOriginalLanguage") withOriginalLanguage: String?,
        @Query("withGenres") withGenres: String?
    ): MoviesResponse
}