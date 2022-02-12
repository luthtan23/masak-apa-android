package com.luthtan.base_architecture_android.domain.baseusecase.coroutines

import com.luthtan.base_architecture_android.domain.subscriber.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class CoroutinesUseCase<PARAM, T : Any> {

    abstract suspend fun build(param: PARAM): T?

    suspend fun executable(param: PARAM) = flow {
        emit(ResultState.Loading())
        emit(ResultState.Success(build(param)))
    }.flowOn(Dispatchers.IO).catch { exception ->
        with(exception) {
            printStackTrace()
            emit(ResultState.Error(exception))
        }
    }
}