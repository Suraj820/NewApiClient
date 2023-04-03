package com.example.newapiclient.domain.usecase

import com.example.newapiclient.domain.repository.NewsRepository

class GetSearchedNewsUseCase (private val newsRepository: NewsRepository) {
    suspend fun execute(searchQuery : String) = newsRepository.getSearchedNews(searchQuery)
}