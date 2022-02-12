package com.luthtan.masak_apa_android.di

import com.luthtan.masak_apa_android.base.config.WebApiProvider
import com.luthtan.masak_apa_android.base.util.SessionHelper
import com.luthtan.masak_apa_android.data.datasource.MyDataSourceImpl
import com.luthtan.masak_apa_android.data.remote.ApiService
import com.luthtan.masak_apa_android.data.repository.MyRepositoryImpl
import com.luthtan.masak_apa_android.domain.datasource.MyDataSource
import com.luthtan.masak_apa_android.domain.repository.MyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
@OptIn(ExperimentalCoroutinesApi::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideWebApiProvider(): WebApiProvider = WebApiProvider

    @Singleton
    @Provides
    fun provideOkhttpBuilder(
        myApplication: com.luthtan.masak_apa_android.MyApplication
    ): OkHttpClient.Builder = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)

    @Singleton
    @Provides
    fun provideRetrofit(
        webApiProvider: WebApiProvider,
        myApplication: com.luthtan.masak_apa_android.MyApplication,
        sessionHelper: SessionHelper,
        okHttpClientBuilder: OkHttpClient.Builder
    ): Retrofit = webApiProvider.getRetrofit(
        myApplication.getBaseUrl(),
        myApplication,
        sessionHelper,
        okHttpClientBuilder
    )

    @Singleton
    @Provides
    fun provideMyDataSource(apiService: ApiService): MyDataSource =
        MyDataSourceImpl(apiService)

    @Singleton
    @Provides
    fun provideMyRepository(source: MyDataSource): MyRepository =
        MyRepositoryImpl(source)

    @Singleton
    @Provides
    fun provideHome01Api(retrofit: Retrofit): ApiService = retrofit.create(
        ApiService::class.java
    )
}