package com.luthtan.masak_apa_android.feature.home.sort

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.luthtan.masak_apa_android.base.BaseViewModel
import com.luthtan.masak_apa_android.data.dtos.SortByModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BottomSheetSortByFilterViewModel @Inject constructor() : BaseViewModel() {

    private val _modelSortBy = MutableLiveData<MutableList<SortByModel>>()
    val modelSortBy: LiveData<MutableList<SortByModel>> = _modelSortBy

    init {
        _modelSortBy.value = mutableListOf(
            SortByModel("Show all Categories", true),
            SortByModel("Show by Categories", false)
        )
    }

    fun initData(sortByModel: SortByModel) {
        val temp = mutableListOf<SortByModel>()
        modelSortBy.value?.forEachIndexed { _, sortBy ->
            sortBy.selected = sortByModel.title == sortBy.title
            temp.add(sortBy)
        }
        _modelSortBy.value = temp
    }


}