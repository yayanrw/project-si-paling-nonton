package com.heyproject.sipalingnonton.data.remote

import com.heyproject.sipalingnonton.core.BEARER_TOKEN
import com.heyproject.sipalingnonton.data.remote.dto.GenresDto
import com.heyproject.sipalingnonton.data.remote.dto.MovieCreditsDto
import com.heyproject.sipalingnonton.data.remote.dto.MovieDetailDto
import com.heyproject.sipalingnonton.data.remote.dto.PersonDto
import com.heyproject.sipalingnonton.data.remote.response.NowPlayingMovieResponse
import com.heyproject.sipalingnonton.data.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface MovieApi {
    @Headers("Authorization: Bearer $BEARER_TOKEN")
    @GET("discover/movie?page={page}&sort_by={sortBy}&with_original_language={withOriginalLanguage}&with_genres={withGenre}")
    suspend fun getMovies(
        @Path("page") page: Int?,
        @Path("sortBy") sortBy: String?,
        @Path("withOriginalLanguage") withOriginalLanguage: String?,
        @Path("withGenres") withGenres: String?
    ): MovieResponse
}