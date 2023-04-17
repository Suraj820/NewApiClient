package com.example.newapiclient.data.repository

import com.example.newapiclient.data.model.APIResponse
import com.example.newapiclient.data.model.Article
import com.example.newapiclient.data.repository.dataSource.NewsLocalDataSource
import com.example.newapiclient.data.repository.dataSource.NewsRemoteDataSource
import com.example.newapiclient.data.util.Resource
import com.example.newapiclient.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
): NewsRepository {
    override suspend fun getNewHeadlines(country : String,page : Int): Resource<APIResponse> {
        return  responseToResource(newsRemoteDataSource.getTopHeadLines(country ,page ))
    }

    override suspend fun getSearchedNews(
        country: String,
        searchQuery: String,
        page: Int,
    ): Resource<APIResponse> {
      return responseToResource(newsRemoteDataSource.getSearchedNews(country, searchQuery, page))
    }



    override suspend fun saveNews(article: Article) {
       newsLocalDataSource.saveArticleToDB(article)
    }

    override suspend fun deleteNews(article: Article) {
        TODO("Not yet implemented")
    }

    override fun getSavedNews(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }
    private fun responseToResource(response: Response<APIResponse>):Resource<APIResponse>{
        if (response.isSuccessful){
            response .body()?.let {result->
                return  Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}