package com.luthtan.masak_apa_android.domain.repository

import com.luthtan.masak_apa_android.data.dtos.CategoriesModel
import com.luthtan.masak_apa_android.data.dtos.HomeModel
import com.luthtan.masak_apa_android.data.dtos.MealsModel
import com.luthtan.masak_apa_android.data.dtos.SearchNameModel
import io.reactivex.Flowable
import retrofit2.Response

interface MyRepository {

    suspend fun getDataCoroutine(): Response<CategoriesModel>
    suspend fun getFilterCategories(filterTag: String): Response<MealsModel>
    suspend fun getSearchByName(queryName: String): Response<SearchNameModel>
    suspend fun getDetailById(id: String) : Response<SearchNameModel>
    fun getDataRxJava(): Flowable<HomeModel>

}