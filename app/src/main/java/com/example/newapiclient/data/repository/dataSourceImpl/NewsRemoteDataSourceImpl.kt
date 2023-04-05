package com.example.newapiclient.data.repository.dataSourceImpl

import com.example.newapiclient.data.api.NewsAPIService
import com.example.newapiclient.data.model.APIResponse
import com.example.newapiclient.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsAPIService: NewsAPIService,
    private val country:String,
    private val page : Int
):NewsRemoteDataSource {
    override suspend fun getTopHeadLines(): Response<APIResponse> {
        return newsAPIService.getTopHeadlines(country,page)
    }
}