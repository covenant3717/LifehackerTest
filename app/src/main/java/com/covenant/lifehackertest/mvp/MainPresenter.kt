package com.covenant.lifehackertest.mvp

import androidx.lifecycle.MutableLiveData
import com.covenant.lifehackertest.model.Post
import com.covenant.lifehackertest.network.LifehackerRepository
import com.covenant.lifehackertest.util.mlg
import com.example.kotlin_with_retrofit2.network.Resource
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

/**
 * Created by Evgeny Kuksov 02.03.2020
 */

class MainPresenter(private val view: MainView) : BasePresenter(view), KoinComponent {

    private val repository by lazy { LifehackerRepository() }

    val postsLD by lazy {
        val ld = MutableLiveData<MutableList<Post>>()
        getPosts()
        return@lazy ld
    }

    //==============================================================================================

    private fun getPosts() {
        mlg("getPosts")
        viewModelScope.launch {
            view.showProgress(true)

            val response = repository.getPosts()
            when (response) {
                is Resource.Error -> onErrorResponse(response)
                is Resource.Success -> postsLD.value = response.value
            }

            view.showProgress(false)
        }
    }

}