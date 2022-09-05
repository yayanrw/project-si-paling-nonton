package com.heyproject.sipalingnonton.presentation.detail

import androidx.lifecycle.ViewModel
import com.heyproject.sipalingnonton.domain.model.Movie
import com.heyproject.sipalingnonton.domain.usecase.MovieUsecase

class DetailViewModel(private val movieUsecase: MovieUsecase) : ViewModel() {
    fun setFavoriteTourism(movie: Movie, newStatus: Boolean) =
        movieUsecase.setFavoriteMovie(movie = movie, newStatus)
}