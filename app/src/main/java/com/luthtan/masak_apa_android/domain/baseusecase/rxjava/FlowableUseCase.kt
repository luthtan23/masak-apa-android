package com.luthtan.masak_apa_android.domain.baseusecase.rxjava

import com.luthtan.masak_apa_android.domain.PostExecutionThread
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber

abstract class FlowableUseCase<T, in Params>(private val postExecutionThread: PostExecutionThread) {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    /**
     * Builds an [Flowable] which will be used when executing the current [FlowableUseCase].
     */
    abstract fun build(params: Params): Flowable<T>

    fun executable(subscriber: DisposableSubscriber<T>, params: Params) {
        val disposable = build(params)
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.getScheduler())
            .subscribeWith(subscriber)
        addDisposable(disposable)
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    fun dispose() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    private fun addDisposable(disposable: Disposable?) {
        if (compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }

        compositeDisposable.add(disposable!!)
    }
}