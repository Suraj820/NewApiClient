package com.example.newapiclient.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newapiclient.data.model.APIResponse
import com.example.newapiclient.data.util.Resource
import com.example.newapiclient.domain.usecase.GetNewsHeadlinesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(
    private val app: Application,
    private  val getNewsHeadlinesUseCase : GetNewsHeadlinesUseCase
) : AndroidViewModel(app){
    val newsHeadlines: MutableLiveData<Resource<APIResponse>> = MutableLiveData()


    fun getNewsHeadlines(country : String,page: Int){
        newsHeadlines.postValue(Resource.Loading())
        try {
            if (isInternetAvailable(app))
                viewModelScope.launch(Dispatchers.IO) {
                    val apiResult = getNewsHeadlinesUseCase.execute(country,page)
                    newsHeadlines.postValue(apiResult)
                }else{
                newsHeadlines.postValue(Resource.Error("Internet is not Available"))
            }
        }catch (e: Exception){
            newsHeadlines.postValue(Resource.Error(e.message.toString()))
        }

    }
    @Suppress("DEPRECATION")
    fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = true
                    }
                }
            }
        }
        return result
    }

}