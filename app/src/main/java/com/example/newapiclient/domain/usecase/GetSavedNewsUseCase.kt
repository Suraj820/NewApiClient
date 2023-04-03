package com.example.newapiclient.domain.usecase

import com.example.newapiclient.domain.repository.NewsRepository

class GetSavedNewsUseCase(private val  newsRepository: NewsRepository) {
 fun execute() = newsRepository.getSavedNews()

}