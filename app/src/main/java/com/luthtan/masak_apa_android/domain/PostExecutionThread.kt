package com.luthtan.masak_apa_android.domain

import io.reactivex.Scheduler

interface PostExecutionThread {
    fun getScheduler(): Scheduler
}