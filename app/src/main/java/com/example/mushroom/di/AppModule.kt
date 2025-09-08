package com.example.mushroom.di

import androidx.room.Room
import com.example.mushroom.repository.DataRepository
import com.example.mushroom.repository.database.AppDatabase
import com.example.mushroom.viewmodel.HomePageViewModel
import com.example.mushroom.viewmodel.MonitoringViewModel
import com.example.mushroom.viewmodel.ShelfDetailsViewModel
import com.example.mushroom.viewmodel.ShelfViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { HomePageViewModel(dataRepository = get()) }
    viewModel { ShelfViewModel(shelfDao = get()) }
    viewModel { (initialId: Int, shelfIds: List<Int>) ->
        MonitoringViewModel(
            initialId,
            shelfIds
        )
    }
    viewModel { (initialId: Int, shelfIds: List<Int>) ->
        ShelfDetailsViewModel(
            initialId,
            shelfIds
        )
    }
}

val repoModule = module {
    factory<DataRepository> { provideDataRepository() }
}