package com.heyproject.sipalingnonton.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.heyproject.core.domain.usecase.MovieUsecase

class HomeViewModel(movieUsecase: MovieUsecase) : ViewModel() {
    val movie = movieUsecase.getMovies().asLiveData()
}