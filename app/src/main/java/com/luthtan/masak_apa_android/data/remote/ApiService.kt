package com.luthtan.masak_apa_android.data.remote

import com.luthtan.masak_apa_android.data.dtos.CategoriesModel
import com.luthtan.masak_apa_android.data.dtos.HomeModel
import com.luthtan.masak_apa_android.data.dtos.MealsModel
import com.luthtan.masak_apa_android.data.dtos.SearchNameModel
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("categories.php")
    suspend fun getDataCoroutine() : Response<CategoriesModel>

    @GET("filter.php")
    suspend fun getFilterCategories(
        @Query("c") filterStr: String
    ) : Response<MealsModel>

    @GET("search.php")
    suspend fun getSearchByName(
        @Query("s") queryName: String
    ) : Response<SearchNameModel>

    @GET("lookup.php")
    suspend fun getDetailById(
        @Query("i") id: String
    ) : Response<SearchNameModel>

    @POST("sample/post/json")
    fun getDataRxJava() : Flowable<HomeModel>

    /*@GET("3/discover/movie")
    fun getDataWithApiKey(
        @Query("api_key") apiKey: String,
        @QueryMap map: HashMap<String, Any>
    ): Flowable<HomeModel>

    @GET("3/movie/{movieId}/reviews")
    fun getDataWithPath(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String
    ): Flowable<HomeModel>*/
}