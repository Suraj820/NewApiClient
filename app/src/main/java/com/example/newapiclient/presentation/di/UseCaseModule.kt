package com.example.newapiclient.presentation.di

import com.example.newapiclient.domain.repository.NewsRepository
import com.example.newapiclient.domain.usecase.GetNewsHeadlinesUseCase
import com.example.newapiclient.domain.usecase.GetSavedNewsUseCase
import com.example.newapiclient.domain.usecase.GetSearchedNewsUseCase
import com.example.newapiclient.domain.usecase.SaveNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideNewsHeadLinesUseCase(newsRepository: NewsRepository): GetNewsHeadlinesUseCase{
        return GetNewsHeadlinesUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun provideGetSearchedNewsUseCase(newsRepository: NewsRepository): GetSearchedNewsUseCase{
        return GetSearchedNewsUseCase(newsRepository)
    }


    @Singleton
    @Provides
    fun provideSavedNewsUseCase(newsRepository: NewsRepository): SaveNewsUseCase{
        return SaveNewsUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun provideGetSavedNewsUseCase(newsRepository: NewsRepository): GetSavedNewsUseCase{
        return GetSavedNewsUseCase(newsRepository)
    }
}