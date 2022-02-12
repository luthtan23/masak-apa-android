package com.luthtan.base_architecture_android.data.remote

import com.luthtan.base_architecture_android.data.dtos.HomeModel
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.POST

interface ApiService {

    @POST("sample/post/json")
    suspend fun getDataCoroutine() : Response<HomeModel>

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