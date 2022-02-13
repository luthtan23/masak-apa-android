package com.luthtan.masak_apa_android.data.dtos

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealsModel(

	@field:SerializedName("meals")
	val meals: List<MealsItemModel>? = null
) : Parcelable

@Parcelize
data class MealsItemModel(

	@field:SerializedName("strMealThumb")
	val strMealThumb: String? = null,

	@field:SerializedName("idMeal")
	val idMeal: String? = null,

	@field:SerializedName("strMeal")
	val strMeal: String? = null
) : Parcelable
