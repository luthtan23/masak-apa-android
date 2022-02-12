package com.luthtan.masak_apa_android.domain.interactors

import com.luthtan.masak_apa_android.data.dtos.HomeModel
import com.luthtan.masak_apa_android.domain.PostExecutionThread
import com.luthtan.masak_apa_android.domain.baseusecase.rxjava.FlowableUseCase
import com.luthtan.masak_apa_android.domain.repository.MyRepository
import com.luthtan.masak_apa_android.domain.subscriber.DefaultSubscriber
import com.luthtan.masak_apa_android.domain.subscriber.ResultState
import io.reactivex.Flowable

class GetDataRxJava(
    private val repository: MyRepository,
    postExecutionThread: PostExecutionThread
) : FlowableUseCase<HomeModel, GetDataRxJava.Param>(postExecutionThread) {
    override fun build(params: Param): Flowable<HomeModel> = repository.getDataRxJava()

    fun execute(params: Param, onData: (data: ResultState<HomeModel>) -> Unit) {
        executable(object : DefaultSubscriber<HomeModel>() {
            override fun onError(error: ResultState<HomeModel>) {
                onData.invoke(error)
            }

            override fun onSuccess(data: ResultState<HomeModel>) {
                onData.invoke(data)
            }

        }, params)
    }

    class Param {

    }
}