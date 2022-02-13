package com.luthtan.masak_apa_android.base.util

import android.graphics.Color
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.google.android.material.imageview.ShapeableImageView
import com.luthtan.masak_apa_android.R
import com.luthtan.masak_apa_android.data.dtos.SortByModel

object BindingAdapter {

    @BindingAdapter("imageWithUrl")
    @JvmStatic
    fun ShapeableImageView.bindImageWithUrl(url: String?) {
        url.let {
            if (it != null) {
                GlideHelper.showThumbnail(url!!, this, context)
            } else {

            }
        }
    }

    @BindingAdapter("sortBySelected")
    @JvmStatic
    fun AppCompatButton.bindSortBySelected(model: SortByModel) {
        this.text = model.title
        if (model.selected) {
            this.setTextColor(Color.WHITE)
            this.setBackgroundColor(ContextCompat.getColor(context, R.color.colorSecondary))
        } else {
            this.setTextColor(Color.BLACK)
            this.setBackgroundColor(ContextCompat.getColor(context, R.color.linked_account_button_disable))
        }
    }

    @BindingAdapter("filterTagSelected")
    @JvmStatic
    fun AppCompatTextView.bindSortBySelected(model: SortByModel) {
        this.text = model.title
        if (model.selected) {
            this.setBackgroundColor(ContextCompat.getColor(context, R.color.soft_blue_3975AC))
        } else {
            this.setBackgroundColor(ContextCompat.getColor(context, R.color.soft_blue_A2C3E1))
        }
    }
}