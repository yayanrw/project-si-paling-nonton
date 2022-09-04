package com.heyproject.sipalingnonton.di

import com.heyproject.sipalingnonton.domain.usecase.MovieInteractor
import com.heyproject.sipalingnonton.domain.usecase.MovieUsecase
import com.heyproject.sipalingnonton.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val usecaseModule = module {
    factory<MovieUsecase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}