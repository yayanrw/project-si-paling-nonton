package com.heyproject.sipalingnonton.di

import com.heyproject.core.domain.usecase.MovieInteractor
import com.heyproject.core.domain.usecase.MovieUsecase
import com.heyproject.sipalingnonton.presentation.detail.DetailViewModel
import com.heyproject.sipalingnonton.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val usecaseModule = module {
    factory<MovieUsecase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}