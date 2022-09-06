package com.heyproject.sipalingnonton.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.heyproject.core.domain.usecase.MovieUsecase

class FavoriteViewModel(movieUsecase: MovieUsecase) : ViewModel() {
    val favoriteMovies = movieUsecase.getFavoriteMovies().asLiveData()
}