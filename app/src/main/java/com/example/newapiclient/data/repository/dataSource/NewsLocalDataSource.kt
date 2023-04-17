package com.example.newapiclient.data.repository.dataSource

import com.example.newapiclient.data.model.Article

interface NewsLocalDataSource {
    suspend fun saveArticleToDB(article: Article)
}