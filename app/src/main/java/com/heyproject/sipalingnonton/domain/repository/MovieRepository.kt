package com.heyproject.sipalingnonton.domain.repository

import com.heyproject.sipalingnonton.core.Resource
import com.heyproject.sipalingnonton.domain.model.*
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<Resource<List<Movie>>>
    fun searchMovies(search: String): Flow<Resource<List<Movie>>>
}