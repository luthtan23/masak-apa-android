package com.luthtan.masak_apa_android.domain.interactors

import com.luthtan.masak_apa_android.data.dtos.SearchNameModel
import com.luthtan.masak_apa_android.domain.baseusecase.coroutines.CoroutinesUseCase
import com.luthtan.masak_apa_android.domain.repository.MyRepository
import com.luthtan.masak_apa_android.domain.subscriber.ResultState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Response

class GetDetailById(
    private val repository: MyRepository
) : CoroutinesUseCase<GetDetailById.Param, Response<SearchNameModel>>() {

    override suspend fun build(param: Param): Response<SearchNameModel> =
        repository.getDetailById(param.id)

    fun execute(
        coroutineScope: CoroutineScope,
        param: Param,
        onResponse: (data: ResultState<Response<SearchNameModel>?>) -> Unit
    ) {
        coroutineScope.launch {
            executable(param).collect {
                onResponse.invoke(it)
            }
        }
    }

    class Param(
        val id: String = ""
    )
}