package com.example.newapiclient.domain.usecase

import com.example.newapiclient.domain.repository.NewsRepository

class GetSearchedNewsUseCase (private val newsRepository: NewsRepository) {
    suspend fun execute(country : String,searchQuery: String,page:Int) = newsRepository.getSearchedNews(country, searchQuery, page)
}