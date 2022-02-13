package com.luthtan.masak_apa_android.base.util

import com.luthtan.masak_apa_android.base.config.Constants
import com.luthtan.masak_apa_android.data.dtos.CategoriesItem
import com.luthtan.masak_apa_android.data.dtos.MealsItemModel

object ShimmerData {

    private fun getCategoriesItem(): CategoriesItem = CategoriesItem(idCategory = Constants.SHIMMER_LOADING)

    fun getShimmerCategories(): List<CategoriesItem> {
        return listOf(getCategoriesItem(), getCategoriesItem(), getCategoriesItem(), getCategoriesItem())
    }

    private fun getMealsItem(): MealsItemModel = MealsItemModel(idMeal = Constants.SHIMMER_LOADING)

    fun getShimmerMeals(): List<MealsItemModel> {
        return listOf(getMealsItem(), getMealsItem(), getMealsItem(), getMealsItem())
    }
}