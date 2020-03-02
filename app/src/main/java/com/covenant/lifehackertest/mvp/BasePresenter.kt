package com.covenant.lifehackertest.mvp

import com.example.kotlin_with_retrofit2.network.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BasePresenter(private val view: MainView) {

    var viewModelJob = Job()
    val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    //==============================================================================================

    fun onDestroy() {
        viewModelJob.cancel()
    }

    //==============================================================================================

    fun onErrorResponse(response: Resource.Error) {
        val code = response.errorCode ?: ""
        val msg = response.errorMessage ?: ""

        view.showError("Ошибка $code $msg")
    }

}