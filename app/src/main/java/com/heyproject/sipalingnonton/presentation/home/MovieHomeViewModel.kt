package com.heyproject.sipalingnonton.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heyproject.sipalingnonton.core.Resource
import com.heyproject.sipalingnonton.domain.model.Movie
import com.heyproject.sipalingnonton.presentation.util.MovieState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieHomeViewModel @Inject constructor(
    private val getMoviesUsecase: GetMovies
) : ViewModel() {
    private val _state = MutableLiveData<MovieState>()
    val state: LiveData<MovieState> = _state

    private val _message = MutableLiveData<String?>()
    val message: LiveData<String?> = _message

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    fun getMovies() {
        viewModelScope.launch {
            getMoviesUsecase().collect() { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = MovieState.SUCCESS
                    }
                    is Resource.Error -> {
                        _state.value = MovieState.ERROR
                        _message.value = result.message
                    }
                    is Resource.Loading -> {
                        _state.value = MovieState.LOADING
                    }
                }
            }
        }
    }
}