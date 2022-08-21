package com.heyproject.sipalingnonton.data.remote

import com.heyproject.sipalingnonton.data.remote.dto.GenresDto
import com.heyproject.sipalingnonton.data.remote.dto.MovieDetailDto
import com.heyproject.sipalingnonton.data.remote.response.DiscoverMovieResponse
import com.heyproject.sipalingnonton.data.remote.response.SearchMovieResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {
    @GET("discover/movie?page={page}&sort_by={sortBy}&with_original_language={withOriginalLanguage}&with_genres={withGenre}")
    suspend fun getDiscoverMovie(
        @Path("page") page: Int,
        @Path("sortBy") sortBy: String,
        @Path("withOriginalLanguage") withOriginalLanguage: String,
        @Path("withGenres") withGenres: String
    ): DiscoverMovieResponse

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: Int
    ): MovieDetailDto

    @GET("search/movie?query={query}")
    suspend fun searchMovie(
        @Path("query") query: String
    ): SearchMovieResponse

    @GET("genre/movie/list")
    suspend fun getMovieGenre() : GenresDto


}