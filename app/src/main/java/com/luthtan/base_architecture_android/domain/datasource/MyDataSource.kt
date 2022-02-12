package com.luthtan.base_architecture_android.domain.datasource

import com.luthtan.base_architecture_android.data.dtos.HomeModel
import io.reactivex.Flowable
import retrofit2.Response

interface MyDataSource {

    suspend fun getDataCoroutine(): Response<HomeModel>
    fun getDataRxJava(): Flowable<HomeModel>

}