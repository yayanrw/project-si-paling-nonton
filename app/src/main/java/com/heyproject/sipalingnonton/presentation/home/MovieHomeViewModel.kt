package com.heyproject.sipalingnonton.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.heyproject.sipalingnonton.domain.usecase.MovieUsecase

class MovieHomeViewModel(movieUsecase: MovieUsecase) : ViewModel() {
    val movies = movieUsecase.getMovies().asLiveData()

}