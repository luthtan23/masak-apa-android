package com.luthtan.masak_apa_android.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.luthtan.masak_apa_android.base.util.SessionHelper
import com.luthtan.masak_apa_android.domain.AndroidUIThread
import com.luthtan.masak_apa_android.domain.PostExecutionThread
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
    fun provideApplication(@ApplicationContext app: Context): com.luthtan.masak_apa_android.MyApplication {
        return app as com.luthtan.masak_apa_android.MyApplication
    }

    @Provides
    @Singleton
    fun provideSessionHelper(myApplication: com.luthtan.masak_apa_android.MyApplication, gson: Gson): SessionHelper {
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