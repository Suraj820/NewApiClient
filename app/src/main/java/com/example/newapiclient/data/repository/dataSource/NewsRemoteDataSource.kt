package com.example.newapiclient.data.repository.dataSource

import com.example.newapiclient.data.model.APIResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getTopHeadLines():Response<APIResponse>
}