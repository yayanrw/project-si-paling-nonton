package com.heyproject.sipalingnonton.data.repository

import com.heyproject.sipalingnonton.data.local.dao.MovieDao
import com.heyproject.sipalingnonton.data.remote.MovieApi

class MovieRepositoryImpl(
    private val api: MovieApi,
    private val dao: MovieDao
) {
}