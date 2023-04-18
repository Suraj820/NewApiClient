package com.example.newapiclient.presentation.di

import com.example.newapiclient.data.db.ArticleDAO
import com.example.newapiclient.data.repository.NewsRepositoryImpl
import com.example.newapiclient.data.repository.dataSource.NewsLocalDataSource
import com.example.newapiclient.data.repository.dataSourceImpl.NewsLocalDataSourceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NewsLocalDataModule {
    @Provides
    @Singleton
    fun provideLocalDataSource(articleDAO: ArticleDAO):NewsLocalDataSource{
        return NewsLocalDataSourceImp(articleDAO)
    }
}