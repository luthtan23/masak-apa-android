package com.luthtan.base_architecture_android.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luthtan.base_architecture_android.base.util.SingleEvents

abstract class BaseViewModel : ViewModel() {
    fun onComplete() {
        // No-op by default
    }

    fun onDataEmpty() {
        // No-op by default
    }

    fun resume() {
        // No-op by default
    }

    fun pause() {
        // No-op by default
    }

    fun destroy() {
        // No-op by default
    }

    protected val _showToast = MutableLiveData<SingleEvents<String>>()
    val showToast: LiveData<SingleEvents<String>> = _showToast
}