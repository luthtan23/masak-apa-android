package com.luthtan.masak_apa_android.feature.filter_categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.luthtan.masak_apa_android.base.BaseViewModel
import com.luthtan.masak_apa_android.base.util.SingleEvents
import com.luthtan.masak_apa_android.data.dtos.MealsItemModel
import com.luthtan.masak_apa_android.data.dtos.MealsModel
import com.luthtan.masak_apa_android.data.dtos.SearchNameModel
import com.luthtan.masak_apa_android.domain.interactors.GetMeals
import com.luthtan.masak_apa_android.domain.interactors.GetSearchMeals
import com.luthtan.masak_apa_android.domain.subscriber.ResultState
import com.luthtan.masak_apa_android.feature.common.ErrorListener
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MealByCategoriesViewModel @Inject constructor(
    private val getMeals: GetMeals,
    private val getSearchMeals: GetSearchMeals
) : BaseViewModel(), ErrorListener, MealListener{

    private val _mealsModel = MutableLiveData<ResultState<Response<MealsModel>?>>()
    val mealsModel: LiveData<ResultState<Response<MealsModel>?>> = _mealsModel

    private val _backPressed = MutableLiveData<SingleEvents<Boolean>>()
    val backPressed: LiveData<SingleEvents<Boolean>> = _backPressed

    private val _refresh = MutableLiveData<SingleEvents<Boolean>>()
    val refresh: LiveData<SingleEvents<Boolean>> = _refresh

    private val _searchName = MutableLiveData<ResultState<Response<SearchNameModel>?>>()
    val searchName: LiveData<ResultState<Response<SearchNameModel>?>> = _searchName

    private val tempMeals: MutableList<MealsItemModel> = mutableListOf()

    fun getMeals(filterTag: String) {
        getMeals.execute(viewModelScope, GetMeals.Param(filterTag)) {
            _mealsModel.value = it
            when(it) {
                is ResultState.Success -> tempMeals.addAll(it.data?.body()?.meals!!)
                else -> {}
            }
        }
    }

    fun initQuerySearchMeals(query: String) {
        getSearchMeals.execute(viewModelScope, GetSearchMeals.Param(query)) {
            _searchName.value = it
        }
    }

    override fun onRefreshClick() {
        _refresh.value = SingleEvents(true)
    }

    override fun onClickBackBtn() {
        _backPressed.value = SingleEvents(true)
    }
}