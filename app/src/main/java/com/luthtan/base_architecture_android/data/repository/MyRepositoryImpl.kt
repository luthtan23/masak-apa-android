package com.luthtan.base_architecture_android.data.repository

import com.luthtan.base_architecture_android.data.dtos.HomeModel
import com.luthtan.base_architecture_android.domain.datasource.MyDataSource
import com.luthtan.base_architecture_android.domain.repository.MyRepository
import io.reactivex.Flowable
import retrofit2.Response
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val myDataSource: MyDataSource
) : MyRepository {

    override suspend fun getDataCoroutine(): Response<HomeModel> = myDataSource.getDataCoroutine()
    override fun getDataRxJava(): Flowable<HomeModel> = myDataSource.getDataRxJava()

}