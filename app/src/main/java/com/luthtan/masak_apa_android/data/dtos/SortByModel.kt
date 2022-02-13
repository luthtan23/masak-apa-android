package com.luthtan.masak_apa_android.data.dtos

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SortByModel (
    var title: String = "",
    var selected: Boolean = false
): Parcelable