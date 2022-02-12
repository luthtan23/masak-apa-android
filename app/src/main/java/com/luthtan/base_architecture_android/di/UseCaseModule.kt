package com.luthtan.base_architecture_android.di

import com.luthtan.base_architecture_android.domain.PostExecutionThread
import com.luthtan.base_architecture_android.domain.interactors.GetDataCoroutine
import com.luthtan.base_architecture_android.domain.interactors.GetDataRxJava
import com.luthtan.base_architecture_android.domain.repository.MyRepository
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
}