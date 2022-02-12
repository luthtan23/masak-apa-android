package com.luthtan.masak_apa_android.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.luthtan.masak_apa_android.base.BaseViewModel
import com.luthtan.masak_apa_android.base.util.SingleEvents
import com.luthtan.masak_apa_android.domain.interactors.GetDataCoroutine
import com.luthtan.masak_apa_android.domain.interactors.GetDataRxJava
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
            getDataCoroutine.execute(viewModelScope, param) {
                it.let {

                }
            }
        }
    }
}