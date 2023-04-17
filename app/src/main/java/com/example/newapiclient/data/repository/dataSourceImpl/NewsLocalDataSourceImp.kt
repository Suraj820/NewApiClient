package com.example.newapiclient.data.repository.dataSourceImpl

import com.example.newapiclient.data.db.ArticleDAO
import com.example.newapiclient.data.model.Article
import com.example.newapiclient.data.repository.dataSource.NewsLocalDataSource

class NewsLocalDataSourceImp(private val articleDAO: ArticleDAO):NewsLocalDataSource {
    override suspend fun saveArticleToDB(article: Article) {
        articleDAO.insert(article)

    }
}