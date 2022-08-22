package com.heyproject.sipalingnonton.data.local

import com.heyproject.sipalingnonton.data.local.dao.*
import com.heyproject.sipalingnonton.data.local.entity.*
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val genreDao: GenreDao,
    private val movieCreditDao: MovieCreditDao,
    private val movieDao: MovieDao,
    private val movieDetailDao: MovieDetailDao,
    private val personDao: PersonDao,
    private val similarMovieDao: SimilarMovieDao
) {
    //genreDao
    suspend fun getGenre(): Flow<List<GenreEntity>> = genreDao.getGenre()
    suspend fun insertGenre(genres: List<GenreEntity>) = genreDao.insertGenre(genres)

    //movieCreditDao
    suspend fun getMovieCredit(movieId: Int): Flow<MovieCreditEntity> =
        movieCreditDao.getMovieCredit(movieId)

    suspend fun insertMovieCredit(movieCredit: MovieCreditEntity) =
        movieCreditDao.insertMovieCredit(movieCredit)

    suspend fun deleteMovieCredit() = movieCreditDao.deleteMovieCredit()

    //movieDao
    suspend fun getMovies(): Flow<List<MovieEntity>> = movieDao.getMovies()
    suspend fun getFavoriteMovies(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovies()
    suspend fun getTrendingMovies(): Flow<List<MovieEntity>> = movieDao.getTrendingMovies()
    suspend fun getNowPlayingMovies(): Flow<List<MovieEntity>> = movieDao.getNowPlayingMovies()
    suspend fun getTopRatedMovies(): Flow<List<MovieEntity>> = movieDao.getTopRatedMovies()
    suspend fun searchMovies(search: String): Flow<List<MovieEntity>> =
        movieDao.searchMovies(search)

    suspend fun insertMovie(movies: List<MovieEntity>) = movieDao.insertMovie(movies)
    suspend fun updateFavoriteMovie(movie: MovieEntity) = movieDao.updateFavoriteMovie(movie)
    suspend fun deleteMovies() = movieDao.deleteMovies()

    //movieDetailDao
    suspend fun getMovieDetail(movieId: Int): Flow<MovieDetailEntity> =
        movieDetailDao.getMovieDetail(movieId)

    suspend fun insertMovieDetail(movieDetail: MovieDetailEntity) =
        movieDetailDao.insertMovieDetail(movieDetail)

    suspend fun deleteMovieDetails() = movieDetailDao.deleteMovieDetails()

    //personDao
    suspend fun getPerson(personId: Int): Flow<PersonEntity> = personDao.getPerson(personId)
    suspend fun insertPerson(person: PersonEntity) = personDao.insertPerson(person)
    suspend fun deletePersons() = personDao.deletePersons()

    //similarMovieDao
    suspend fun getSimilarMovies(referenceMovieId: Int): Flow<SimilarMovieEntity> =
        similarMovieDao.getSimilarMovies(referenceMovieId)

    suspend fun insertSimilarMovies(similarMovies: List<SimilarMovieEntity>) =
        similarMovieDao.insertSimilarMovies(similarMovies)

    suspend fun deleteSimilarMovies() = similarMovieDao.deleteSimilarMovies()
}