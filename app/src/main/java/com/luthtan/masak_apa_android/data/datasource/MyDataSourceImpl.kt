package com.luthtan.masak_apa_android.data.datasource

import com.luthtan.masak_apa_android.data.dtos.CategoriesModel
import com.luthtan.masak_apa_android.data.dtos.HomeModel
import com.luthtan.masak_apa_android.data.dtos.MealsModel
import com.luthtan.masak_apa_android.data.dtos.SearchNameModel
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

    override suspend fun getDataCoroutine(): Response<CategoriesModel> = apiService.getDataCoroutine()
    override suspend fun getFilterCategories(filterTag: String): Response<MealsModel> =
        apiService.getFilterCategories(filterTag)

    override suspend fun getSearchByName(queryName: String): Response<SearchNameModel> =
        apiService.getSearchByName(queryName)

    override suspend fun getDetailById(id: String): Response<SearchNameModel> =
        apiService.getDetailById(id)

    override fun getDataRxJava(): Flowable<HomeModel> = apiService.getDataRxJava()
}