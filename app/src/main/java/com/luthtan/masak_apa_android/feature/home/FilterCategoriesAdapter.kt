package com.luthtan.masak_apa_android.feature.home

import com.luthtan.masak_apa_android.base.BaseAdapter
import com.luthtan.masak_apa_android.data.dtos.SortByModel
import com.luthtan.masak_apa_android.databinding.ItemFilterBinding
import com.luthtan.masak_apa_android.feature.common.OnCustomClickListener

class FilterCategoriesAdapter : BaseAdapter<ItemFilterBinding, SortByModel>(
    ItemFilterBinding::inflate
) {

    var onCustomClickListener: OnCustomClickListener? = null

    fun setCustomClickListener(onCustomClickListener: OnCustomClickListener) {
        this.onCustomClickListener = onCustomClickListener
    }

    override fun bind(binding: ItemFilterBinding, data: SortByModel?) {
        if (data != null) {
            with(binding) {
                model = data
                cvFilter.setOnClickListener {
                    onCustomClickListener?.onClick(data)
                }
            }
        }
    }
}