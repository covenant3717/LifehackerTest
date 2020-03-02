package com.covenant.lifehackertest.network

import com.covenant.lifehackertest.model.Post
import com.covenant.lifehackertest.util.mlg
import com.example.kotlin_with_retrofit2.network.Resource
import com.example.kotlin_with_retrofit2.network.safeApiCall
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Created by Evgeny Kuksov 02.03.2020
 */

interface Repository {
    suspend fun getPosts(): Resource<MutableList<Post>>
}

class LifehackerRepository() : Repository, KoinComponent {

    private val mainApi: MainApi by inject()

    //==============================================================================================

    override suspend fun getPosts(): Resource<MutableList<Post>> {
        mlg("getPost safeApi")
        return safeApiCall{ mainApi.getPosts().await() }
    }


}
