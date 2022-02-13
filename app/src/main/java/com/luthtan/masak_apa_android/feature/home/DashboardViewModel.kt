package com.luthtan.masak_apa_android.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.luthtan.masak_apa_android.base.BaseViewModel
import com.luthtan.masak_apa_android.base.util.SingleEvents
import com.luthtan.masak_apa_android.data.dtos.*
import com.luthtan.masak_apa_android.domain.interactors.GetDataCoroutine
import com.luthtan.masak_apa_android.domain.interactors.GetMeals
import com.luthtan.masak_apa_android.domain.interactors.GetSearchMeals
import com.luthtan.masak_apa_android.domain.subscriber.ResultState
import com.luthtan.masak_apa_android.feature.common.ErrorListener
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getDataCoroutine: GetDataCoroutine,
    private val getMeals: GetMeals,
    private val getSearchMeals: GetSearchMeals
) : BaseViewModel(), DashboardListener, ErrorListener {

    private val _goToProfile = MutableLiveData<SingleEvents<Boolean>>()
    val goToProfile: LiveData<SingleEvents<Boolean>> = _goToProfile

    private val _refresh = MutableLiveData<SingleEvents<Boolean>>()
    val refresh: LiveData<SingleEvents<Boolean>> = _refresh

    override fun onClickGoToProfile() {
        _goToProfile.value = SingleEvents(true)
    }

    private val _categoriesData = MutableLiveData<ResultState<Response<CategoriesModel>?>>()
    val categoriesData: LiveData<ResultState<Response<CategoriesModel>?>> = _categoriesData

    private val _mealsModel = MutableLiveData<ResultState<Response<MealsModel>?>>()
    val mealsModel: LiveData<ResultState<Response<MealsModel>?>> = _mealsModel

    private val _searchName = MutableLiveData<ResultState<Response<SearchNameModel>?>>()
    val searchName: LiveData<ResultState<Response<SearchNameModel>?>> = _searchName

    private val _categoriesDataTag = MutableLiveData<List<SortByModel>>()
    val categoriesDataTag: LiveData<List<SortByModel>> = _categoriesDataTag

    private val _selectedSortBy = MutableLiveData<SortByModel>()
    val selectedSortBy: LiveData<SortByModel> = _selectedSortBy

    private val temp: MutableList<CategoriesItem> = mutableListOf()
    private val tempMeals: MutableList<MealsItemModel> = mutableListOf()

    init {
        getDataCoroutine()
        _selectedSortBy.value = SortByModel("Show all Categories", true)
    }

    fun getDataCoroutine() {
        getDataCoroutine.execute(viewModelScope, GetDataCoroutine.Param()) {
            _categoriesData.value = it
            when(it) {
                is ResultState.Success -> {
                    temp.addAll(it.data?.body()?.categories!!)
                    val filterTag = mutableListOf<SortByModel>()
                    it.data.body()?.categories?.forEachIndexed { index, filter ->
                        filterTag.add(SortByModel(filter.strCategory!!, index == 0))
                    }
                    getMeals(filterTag[0].title) // initialize tag
                    _categoriesDataTag.value = filterTag
                }
                else -> {}
            }
        }
    }

    fun getMeals(filterTag: String) {
        getMeals.execute(viewModelScope, GetMeals.Param(filterTag)) {
            _mealsModel.value = it
            when(it) {
                is ResultState.Success -> tempMeals.addAll(it.data?.body()?.meals!!)
                else -> {}
            }
        }
    }

    fun initQuerySearchCategories(query: String) {
        _categoriesData.value = ResultState.Loading()
        val resultSearch = mutableListOf<CategoriesItem>()
        for (category in temp) {
            if (category.strCategory?.lowercase()?.contains(query.lowercase())!!) {
                resultSearch.add(category)
            }
        }
        _categoriesData.value = ResultState.Success(Response.success(CategoriesModel(resultSearch)))
    }

    fun initQuerySearchMeals(query: String) {
        getSearchMeals.execute(viewModelScope, GetSearchMeals.Param(query)) {
            _searchName.value = it
        }
    }

    fun sortByModel(sortByModel: SortByModel) {
        _selectedSortBy.value = sortByModel
    }

    fun tagFilterSelected(sortByModel: SortByModel) {
        val temp = mutableListOf<SortByModel>()
        categoriesDataTag.value?.forEach { sortBy ->
            sortBy.selected = sortByModel.title == sortBy.title
            temp.add(sortBy)
        }
        _categoriesDataTag.value = temp
    }

    override fun onRefreshClick() {
        _refresh.value = SingleEvents(true)
    }
}