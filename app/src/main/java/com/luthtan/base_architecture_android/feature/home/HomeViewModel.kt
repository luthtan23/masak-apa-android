package com.luthtan.base_architecture_android.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.luthtan.base_architecture_android.base.BaseViewModel
import com.luthtan.base_architecture_android.base.util.SingleEvents
import com.luthtan.base_architecture_android.data.dtos.HomeModel
import com.luthtan.base_architecture_android.domain.interactors.GetDataCoroutine
import com.luthtan.base_architecture_android.domain.interactors.GetDataRxJava
import com.luthtan.base_architecture_android.domain.subscriber.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDataCoroutine: GetDataCoroutine,
    private val GetDataRxJava: GetDataRxJava
) : BaseViewModel(), HomeListener {

    private val _goToProfile = MutableLiveData<SingleEvents<Boolean>>()
    val goToProfile: LiveData<SingleEvents<Boolean>> = _goToProfile

    override fun onClickGoToProfile() {
        _goToProfile.value = SingleEvents(true)
    }

    fun initData() {
        viewModelScope.launch {
            val param = GetDataCoroutine.Param()
            getDataCoroutine.execute(viewModelScope, param, {
                it.let {
                    if (it == null) {
                        // loading
                    }
                    // handle success
                }
            }) { error ->
                // error handle
            }
        }
    }
}