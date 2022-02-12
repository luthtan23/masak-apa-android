package com.luthtan.base_architecture_android.base.ext

import android.accounts.NetworkErrorException
import com.luthtan.base_architecture_android.data.dtos.ErrorModel
import com.luthtan.base_architecture_android.domain.subscriber.ResultState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import retrofit2.HttpException
import java.net.HttpURLConnection

@OptIn(InternalCoroutinesApi::class)
suspend inline fun <T> Flow<ResultState<T>>.collectWithHandler(
    crossinline action: suspend (value: T?) -> Unit,
    noinline error: suspend (value: ErrorModel) -> Unit
): Unit =
    collect(object : FlowCollector<ResultState<T>> {
        val CLIENT_ERROR = 5000
        override suspend fun emit(value: ResultState<T>) = when (value) {
            is ResultState.Success -> action(value.data)
            is ResultState.Error -> {
                when (value.throwable) {
                    is HttpException -> {
                        val throwable = value.throwable
                        var errorMessage = "Unknown Error"
                        //TODO put http code error to parse error message
                        when (throwable.code()) {
                            HttpURLConnection.HTTP_INTERNAL_ERROR -> errorMessage =
                                "Server Not Response"
                            HttpURLConnection.HTTP_NOT_FOUND -> errorMessage = "Server Not Found"
                            HttpURLConnection.HTTP_UNAUTHORIZED -> {
                                errorMessage = "Authorization Not Recognize"
                            }
                        }
                        error(ErrorModel(errorMessage, throwable.code()))
                    }
                    is NetworkErrorException -> {
                        error(ErrorModel(value.throwable.message ?: "Network Error", CLIENT_ERROR))
                    }
                    else -> {
                        error(ErrorModel(value.throwable.message ?: "Unknown Error", CLIENT_ERROR))
                    }
                }
            }
            else -> action(null)
        }
    })

