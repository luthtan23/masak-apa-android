package com.luthtan.masak_apa_android.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.luthtan.masak_apa_android.base.BaseViewModel
import com.luthtan.masak_apa_android.data.dtos.SearchNameModel
import com.luthtan.masak_apa_android.domain.interactors.GetDetailById
import com.luthtan.masak_apa_android.domain.subscriber.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDetailById: GetDetailById
): BaseViewModel() {

    private val _detailMeals = MutableLiveData<ResultState<Response<SearchNameModel>?>>()
    val detailMeals: LiveData<ResultState<Response<SearchNameModel>?>> = _detailMeals

    fun getDetailById(id: String) {
        getDetailById.execute(viewModelScope, GetDetailById.Param(id)) {
            _detailMeals.value = it
        }
    }
}