package com.luthtan.masak_apa_android.base

import android.app.Application

abstract class BaseApplication : Application() {
    abstract fun getBaseUrl(): String
}