package com.example.newapiclient.data.repository.dataSourceImpl

import com.example.newapiclient.data.db.ArticleDAO
import com.example.newapiclient.data.model.Article
import com.example.newapiclient.data.repository.dataSource.NewsLocalDataSource
import kotlinx.coroutines.flow.Flow

class NewsLocalDataSourceImp(private val articleDAO: ArticleDAO):NewsLocalDataSource {
    override suspend fun saveArticleToDB(article: Article) {
        articleDAO.insert(article)

    }

    override fun getSavedArticles(): Flow<List<Article>> {
        return articleDAO.getAllArticles()
    }
}