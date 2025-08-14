package com.example.mushroom.di

import com.example.mushroom.repository.DataRepository
import com.example.mushroom.viewmodel.HomePageViewModel
import com.example.mushroom.viewmodel.MonitoringViewModel
import com.example.mushroom.viewmodel.ShelfDetailsViewModel
import com.example.mushroom.viewmodel.ShelfViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { HomePageViewModel(dataRepository = get()) }
    viewModel { MonitoringViewModel() }
    viewModel { ShelfViewModel() }
    viewModel { ShelfDetailsViewModel() }
}

val repoModule = module {
    factory<DataRepository> { provideDataRepository() }
}