package com.luthtan.masak_apa_android.feature.home.sort

import com.luthtan.masak_apa_android.base.BaseAdapter
import com.luthtan.masak_apa_android.data.dtos.SortByModel
import com.luthtan.masak_apa_android.databinding.ItemSortBinding
import com.luthtan.masak_apa_android.feature.common.OnSortByListener

class BottomSheetSortByFilterAdapter : BaseAdapter<ItemSortBinding, SortByModel>(
    ItemSortBinding::inflate
) {

    var onSortByListener: OnSortByListener? = null

    fun setCustomOnClick(onSortByListener: OnSortByListener) {
        this.onSortByListener = onSortByListener
    }

    override fun bind(binding: ItemSortBinding, data: SortByModel?) {
        if (data != null) {
            binding.categories = data
            binding.btnSortBy.setOnClickListener {
                onSortByListener?.onClick(data)
            }
        }
    }
}