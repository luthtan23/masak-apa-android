package com.luthtan.masak_apa_android.data.repository

import com.luthtan.masak_apa_android.data.dtos.CategoriesModel
import com.luthtan.masak_apa_android.data.dtos.HomeModel
import com.luthtan.masak_apa_android.data.dtos.MealsModel
import com.luthtan.masak_apa_android.data.dtos.SearchNameModel
import com.luthtan.masak_apa_android.domain.datasource.MyDataSource
import com.luthtan.masak_apa_android.domain.repository.MyRepository
import io.reactivex.Flowable
import retrofit2.Response
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val myDataSource: MyDataSource
) : MyRepository {

    override suspend fun getDataCoroutine(): Response<CategoriesModel> = myDataSource.getDataCoroutine()
    override suspend fun getFilterCategories(filterTag: String): Response<MealsModel> =
        myDataSource.getFilterCategories(filterTag)

    override suspend fun getSearchByName(queryName: String): Response<SearchNameModel> =
        myDataSource.getSearchByName(queryName)

    override suspend fun getDetailById(id: String): Response<SearchNameModel> =
        myDataSource.getDetailById(id)

    override fun getDataRxJava(): Flowable<HomeModel> = myDataSource.getDataRxJava()

}