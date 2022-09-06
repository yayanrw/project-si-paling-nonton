package com.heyproject.sipalingnonton.presentation.detail

import androidx.lifecycle.ViewModel
import com.heyproject.core.domain.model.Movie
import com.heyproject.core.domain.usecase.MovieUsecase

class DetailViewModel(private val movieUsecase: MovieUsecase) : ViewModel() {
    fun setFavoriteTourism(movie: Movie, newStatus: Boolean) =
        movieUsecase.setFavoriteMovie(movie = movie, newStatus)
}