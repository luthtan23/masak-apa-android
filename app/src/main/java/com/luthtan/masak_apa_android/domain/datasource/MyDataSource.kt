package com.luthtan.masak_apa_android.domain.datasource

import com.luthtan.masak_apa_android.data.dtos.HomeModel
import io.reactivex.Flowable
import retrofit2.Response

interface MyDataSource {

    suspend fun getDataCoroutine(): Response<HomeModel>
    fun getDataRxJava(): Flowable<HomeModel>

}