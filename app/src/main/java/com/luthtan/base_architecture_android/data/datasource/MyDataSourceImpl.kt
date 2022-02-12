package com.luthtan.base_architecture_android.data.datasource

import com.luthtan.base_architecture_android.data.dtos.HomeModel
import com.luthtan.base_architecture_android.data.remote.ApiService
import com.luthtan.base_architecture_android.domain.datasource.MyDataSource
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