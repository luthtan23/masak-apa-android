package com.luthtan.masak_apa_android.feature.profile

import androidx.lifecycle.viewModelScope
import com.luthtan.masak_apa_android.base.BaseViewModel
import com.luthtan.masak_apa_android.domain.interactors.GetDataCoroutine
import com.luthtan.masak_apa_android.domain.interactors.GetDataRxJava
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getDataCoroutine: GetDataCoroutine,
    private val getDataRxJava: GetDataRxJava
) : BaseViewModel() {

    fun getDataCoroutine() {
        getDataCoroutine.execute(viewModelScope, GetDataCoroutine.Param()) {

        }
    }

    fun getDataRxJava() {
        getDataRxJava.execute(GetDataRxJava.Param()) {

        }
    }

}