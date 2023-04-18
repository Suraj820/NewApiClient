package com.example.newapiclient.presentation.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newapiclient.data.db.ArticleDAO
import com.example.newapiclient.data.db.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun providerNewsDatabase(app:Application):ArticleDatabase{
        return Room.databaseBuilder(app,ArticleDatabase::class.java,"news_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun  provideNewsDao(articleDatabase: ArticleDatabase):ArticleDAO{
        return articleDatabase.getArticleDAO()
    }
}