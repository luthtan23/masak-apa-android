package com.luthtan.masak_apa_android.data.datasource

import com.luthtan.masak_apa_android.data.dtos.HomeModel
import com.luthtan.masak_apa_android.data.remote.ApiService
import com.luthtan.masak_apa_android.domain.datasource.MyDataSource
import io.reactivex.Flowable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Response
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MyDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : MyDataSource {
    override suspend fun getDataCoroutine(): Response<HomeModel> = apiService.getDataCoroutine()
    override fun getDataRxJava(): Flowable<HomeModel> = apiService.getDataRxJava()
}