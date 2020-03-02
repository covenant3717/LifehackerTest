package com.covenant.lifehackertest.network

import com.covenant.lifehackertest.model.Post
import kotlinx.coroutines.Deferred
import retrofit2.http.*

/**
 * Created by Evgeny Kuksov 02.03.2020
 */

interface MainApi {

    @GET("posts")
    fun getPosts(): Deferred<MutableList<Post>>
}