package com.luthtan.masak_apa_android.feature.common

import com.luthtan.masak_apa_android.data.dtos.CategoriesItem
import com.luthtan.masak_apa_android.data.dtos.MealsItemModel
import com.luthtan.masak_apa_android.data.dtos.SortByModel

interface OnCustomClickListener {
    fun onClick(categoriesItem: CategoriesItem) {}
    fun onClick(mealsModel: MealsItemModel) {}
    fun onClick(sortByModel: SortByModel) {}
}

interface OnSortByListener {
    fun onClick(sortByModel: SortByModel)
}