package com.heyproject.sipalingnonton.data.local

import com.heyproject.sipalingnonton.data.local.dao.*
import com.heyproject.sipalingnonton.data.local.entity.*
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val movieDao: MovieDao,
) {
    //movieDao
    fun getMovies(): Flow<List<MovieEntity>> = movieDao.getMovies()
}