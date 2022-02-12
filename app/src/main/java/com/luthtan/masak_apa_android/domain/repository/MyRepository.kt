package com.luthtan.masak_apa_android.domain.repository

import com.luthtan.masak_apa_android.data.dtos.HomeModel
import io.reactivex.Flowable
import retrofit2.Response

interface MyRepository {

    suspend fun getDataCoroutine(): Response<HomeModel>
    fun getDataRxJava(): Flowable<HomeModel>

}