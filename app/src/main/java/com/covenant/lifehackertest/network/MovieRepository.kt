package com.covenant.lifehackertest.network

import com.covenant.lifehackertest.model.Post
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
        return safeApiCall{ mainApi.getPosts().await() }
    }


}
