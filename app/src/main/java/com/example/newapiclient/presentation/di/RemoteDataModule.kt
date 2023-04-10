package com.example.newapiclient.presentation.di

import com.example.newapiclient.data.api.NewsAPIService
import com.example.newapiclient.data.repository.dataSource.NewsRemoteDataSource
import com.example.newapiclient.data.repository.dataSourceImpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(newsAPIService: NewsAPIService):NewsRemoteDataSource{
        return NewsRemoteDataSourceImpl(newsAPIService)
    }
}