package com.example.newapiclient.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newapiclient.domain.usecase.GetNewsHeadlinesUseCase
import com.example.newapiclient.domain.usecase.GetSearchedNewsUseCase
import com.example.newapiclient.domain.usecase.SaveNewsUseCase

@Suppress("UNCHECKED_CAST")
class NewsViewModelFactory(
    private val app: Application,
    private  val getNewsHeadlinesUseCase : GetNewsHeadlinesUseCase,
    private  val getSearchedNewsUseCase: GetSearchedNewsUseCase,
    private  val saveNewUseCase: SaveNewsUseCase
    ):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(app,getNewsHeadlinesUseCase,getSearchedNewsUseCase,saveNewUseCase) as T
    }
}