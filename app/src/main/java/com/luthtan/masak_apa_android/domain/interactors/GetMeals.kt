package com.luthtan.masak_apa_android.domain.interactors

import com.luthtan.masak_apa_android.data.dtos.MealsModel
import com.luthtan.masak_apa_android.domain.baseusecase.coroutines.CoroutinesUseCase
import com.luthtan.masak_apa_android.domain.repository.MyRepository
import com.luthtan.masak_apa_android.domain.subscriber.ResultState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Response

class GetMeals(private val repository: MyRepository) : CoroutinesUseCase<GetMeals.Param, Response<MealsModel>>() {

    override suspend fun build(param: Param): Response<MealsModel> = repository.getFilterCategories(param.filterTag)

    fun execute(coroutineScope: CoroutineScope, param: Param, onResponse: (data: ResultState<Response<MealsModel>?>) -> Unit) {
        coroutineScope.launch {
            executable(param).collect {
                onResponse.invoke(it)
            }
        }
    }

    class Param (
        var filterTag: String = ""
    )
}