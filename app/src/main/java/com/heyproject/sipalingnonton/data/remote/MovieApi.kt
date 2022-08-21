package com.heyproject.sipalingnonton.data.remote

import com.heyproject.sipalingnonton.data.remote.dto.GenresDto
import com.heyproject.sipalingnonton.data.remote.dto.MovieCreditsDto
import com.heyproject.sipalingnonton.data.remote.dto.MovieDetailDto
import com.heyproject.sipalingnonton.data.remote.dto.PersonDto
import com.heyproject.sipalingnonton.data.remote.response.NowPlayingMovieResponse
import com.heyproject.sipalingnonton.data.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {
    @GET("discover/movie?page={page}&sort_by={sortBy}&with_original_language={withOriginalLanguage}&with_genres={withGenre}")
    suspend fun getDiscoverMovie(
        @Path("page") page: Int,
        @Path("sortBy") sortBy: String,
        @Path("withOriginalLanguage") withOriginalLanguage: String,
        @Path("withGenres") withGenres: String
    ): MovieResponse

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: Int
    ): MovieDetailDto

    @GET("search/movie?query={query}")
    suspend fun searchMovie(
        @Path("query") query: String
    ): MovieResponse

    @GET("movie/now_playing?region={region}")
    suspend fun getNowPlayingMovie(): NowPlayingMovieResponse

    @GET("trending/movie/week")
    suspend fun getTrendingMovie(): MovieResponse

    @GET("movie/{movieId}/similar")
    suspend fun getSimilarMovie(
        @Path("movieId") movieId: Int
    ): MovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(): MovieResponse

    @GET("movie/{movieId}/credits")
    suspend fun getMovieCredits(): MovieCreditsDto

    @GET("person/{personId}")
    suspend fun getPerson(): PersonDto

    @GET("genre/movie/list")
    suspend fun getMovieGenre(): GenresDto
}