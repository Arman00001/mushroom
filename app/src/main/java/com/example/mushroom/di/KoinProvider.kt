package com.example.mushroom.di

import com.example.mushroom.repository.DataRepository
import com.example.mushroom.repository.DataRepositoryImpl

fun provideDataRepository(): DataRepository {
    return DataRepositoryImpl()
}
