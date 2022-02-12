package com.luthtan.base_architecture_android.domain.repository

import com.luthtan.base_architecture_android.data.dtos.HomeModel
import io.reactivex.Flowable
import retrofit2.Response

interface MyRepository {

    suspend fun getDataCoroutine(): Response<HomeModel>
    fun getDataRxJava(): Flowable<HomeModel>

}