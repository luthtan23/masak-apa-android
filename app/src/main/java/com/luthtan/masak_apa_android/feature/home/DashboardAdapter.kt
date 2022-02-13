package com.luthtan.masak_apa_android.feature.home

import android.view.View
import com.luthtan.masak_apa_android.base.BaseAdapter
import com.luthtan.masak_apa_android.base.config.Constants
import com.luthtan.masak_apa_android.data.dtos.CategoriesItem
import com.luthtan.masak_apa_android.databinding.ItemCategoriesBinding
import com.luthtan.masak_apa_android.feature.common.OnCustomClickListener

class DashboardAdapter : BaseAdapter<ItemCategoriesBinding, CategoriesItem>(
    ItemCategoriesBinding::inflate
) {

    var onCustomClickListener: OnCustomClickListener? = null

    fun setCustomClickListener(onCustomClickListener: OnCustomClickListener) {
        this.onCustomClickListener = onCustomClickListener
    }

    override fun bind(binding: ItemCategoriesBinding, data: CategoriesItem?) {
        if (data != null) {
            if (!data.idCategory.equals(Constants.SHIMMER_LOADING)) {
                with(binding) {
                    model = data
                    shimmerLoading.stopShimmer()
                    shimmerLoading.hideShimmer()
                    shimmerLyt.visibility = View.GONE
                    shimmerLoading.setOnClickListener {
                        onCustomClickListener?.onClick(data)
                    }
                }
            } else {
                with(binding) {
                    shimmerLoading.startShimmer()
                    shimmerLyt.visibility = View.VISIBLE
                }
            }
        }
    }
}