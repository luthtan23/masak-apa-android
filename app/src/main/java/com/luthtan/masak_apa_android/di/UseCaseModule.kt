package com.luthtan.masak_apa_android.di

import com.luthtan.masak_apa_android.domain.PostExecutionThread
import com.luthtan.masak_apa_android.domain.interactors.*
import com.luthtan.masak_apa_android.domain.repository.MyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetDataCoroutine(
        repository: MyRepository
    ): GetDataCoroutine = GetDataCoroutine(repository)

    @Provides
    @ViewModelScoped
    fun provideGetDataRxJava(
        repository: MyRepository,
        postExecutionThread: PostExecutionThread
    ): GetDataRxJava = GetDataRxJava(repository, postExecutionThread)

    @Provides
    @ViewModelScoped
    fun provideGetMeals(
        repository: MyRepository
    ): GetMeals = GetMeals(repository)

    @Provides
    @ViewModelScoped
    fun provideGetSearchMeals(
        repository: MyRepository
    ): GetSearchMeals = GetSearchMeals(repository)

    @Provides
    @ViewModelScoped
    fun provideGetDetailById(
        repository: MyRepository
    ): GetDetailById = GetDetailById(repository)
}