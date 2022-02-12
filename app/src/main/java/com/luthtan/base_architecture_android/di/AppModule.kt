package com.luthtan.base_architecture_android.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.luthtan.base_architecture_android.MyApplication
import com.luthtan.base_architecture_android.base.util.SessionHelper
import com.luthtan.base_architecture_android.domain.AndroidUIThread
import com.luthtan.base_architecture_android.domain.PostExecutionThread
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): MyApplication {
        return app as MyApplication
    }

    @Provides
    @Singleton
    fun provideSessionHelper(myApplication: MyApplication, gson: Gson): SessionHelper {
        return SessionHelper(myApplication, gson)
    }

    @Provides
    @Singleton
    fun providePostExecutionThread(): PostExecutionThread = AndroidUIThread()

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }
}