package com.luthtan.masak_apa_android.data.repository

import com.luthtan.masak_apa_android.data.dtos.HomeModel
import com.luthtan.masak_apa_android.domain.datasource.MyDataSource
import com.luthtan.masak_apa_android.domain.repository.MyRepository
import io.reactivex.Flowable
import retrofit2.Response
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val myDataSource: MyDataSource
) : MyRepository {

    override suspend fun getDataCoroutine(): Response<HomeModel> = myDataSource.getDataCoroutine()
    override fun getDataRxJava(): Flowable<HomeModel> = myDataSource.getDataRxJava()

}