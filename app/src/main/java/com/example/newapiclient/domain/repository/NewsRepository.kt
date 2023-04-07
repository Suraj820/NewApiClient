package com.example.newapiclient.domain.repository

import com.example.newapiclient.data.model.APIResponse
import com.example.newapiclient.data.model.Article
import com.example.newapiclient.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNewHeadlines(country : String,page : Int):Resource<APIResponse>
    suspend fun getSearchedNews(searchQuery :String) : Resource<APIResponse>
    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    fun getSavedNews(): Flow<List<Article>>
}