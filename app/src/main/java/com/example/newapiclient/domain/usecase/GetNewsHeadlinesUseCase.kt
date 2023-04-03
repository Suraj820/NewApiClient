package com.example.newapiclient.domain.usecase

import com.example.newapiclient.data.model.APIResponse
import com.example.newapiclient.data.util.Resource
import com.example.newapiclient.domain.repository.NewsRepository

class GetNewsHeadlinesUseCase(private val  newsRepository: NewsRepository) {
    suspend fun execute():Resource<APIResponse> = newsRepository.getNewHeadlines()
}