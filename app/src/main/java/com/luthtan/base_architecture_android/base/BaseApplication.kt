package com.luthtan.base_architecture_android.base

import android.app.Application

abstract class BaseApplication : Application() {
    abstract fun getBaseUrl(): String
}