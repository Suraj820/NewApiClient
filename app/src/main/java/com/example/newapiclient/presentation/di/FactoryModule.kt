package com.example.newapiclient.presentation.di

import android.app.Application
import com.example.newapiclient.domain.usecase.GetNewsHeadlinesUseCase
import com.example.newapiclient.domain.usecase.GetSavedNewsUseCase
import com.example.newapiclient.domain.usecase.GetSearchedNewsUseCase
import com.example.newapiclient.domain.usecase.SaveNewsUseCase
import com.example.newapiclient.presentation.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Provides
    @Singleton
    fun provideNewsViewModelFactory(
        application: Application,
        getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
        getSearchedNewsUseCase: GetSearchedNewsUseCase,
        savedNewsUseCase: SaveNewsUseCase,
        getSavedNewsUseCase: GetSavedNewsUseCase
    ) : NewsViewModelFactory{
        return NewsViewModelFactory(application,getNewsHeadlinesUseCase,getSearchedNewsUseCase,savedNewsUseCase,getSavedNewsUseCase)
    }
}