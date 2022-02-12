package com.luthtan.masak_apa_android.domain.interactors

import com.luthtan.masak_apa_android.data.dtos.HomeModel
import com.luthtan.masak_apa_android.domain.baseusecase.coroutines.CoroutinesUseCase
import com.luthtan.masak_apa_android.domain.repository.MyRepository
import com.luthtan.masak_apa_android.domain.subscriber.ResultState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Response

class GetDataCoroutine(private val repository: MyRepository) : CoroutinesUseCase<GetDataCoroutine.Param, Response<HomeModel>>() {
    override suspend fun build(param: Param): Response<HomeModel> = repository.getDataCoroutine()

    fun execute(coroutineScope: CoroutineScope, param: Param, onResponse: (data: ResultState<Response<HomeModel>?>) -> Unit) {
        coroutineScope.launch {
            executable(param).collect {
                onResponse.invoke(it)
            }
        }
    }

    class Param {

    }
}