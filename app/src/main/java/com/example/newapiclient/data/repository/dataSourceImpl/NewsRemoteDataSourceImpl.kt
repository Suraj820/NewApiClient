package com.example.newapiclient.data.repository.dataSourceImpl

import com.example.newapiclient.data.api.NewsAPIService
import com.example.newapiclient.data.model.APIResponse
import com.example.newapiclient.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsAPIService: NewsAPIService,
):NewsRemoteDataSource {
    override suspend fun getTopHeadLines(country : String,page : Int): Response<APIResponse> {
        return newsAPIService.getTopHeadlines(country,page)
    }
}