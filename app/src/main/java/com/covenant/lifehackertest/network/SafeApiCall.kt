package com.example.kotlin_with_retrofit2.network

import com.covenant.lifehackertest.util.mlg
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by Evgeny Kuksov 31.01.2020
 */

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Error(val errorCode: Int? = null, val errorMessage: String? = "") : Resource<Nothing>()
}

//==================================================================================================

suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
    return withContext(Dispatchers.IO) {
        try {

            val response = apiCall.invoke()
            Resource.Success(response)

        } catch (throwable: Throwable) {
            mlg(throwable.toString())

            when (throwable) {
                is IOException -> Resource.Error(null, throwable.message)

                is HttpException -> Resource.Error(throwable.code(), throwable.response()?.message())

                else -> Resource.Error(null, null)
            }

        }
    }
}
