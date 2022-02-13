package com.luthtan.masak_apa_android.base.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.luthtan.masak_apa_android.R

object GlideHelper {

    fun showThumbnail(url: String, imageView: ImageView, context: Context) {
        val options = RequestOptions().fitCenter()
            .error(R.drawable.ic_baseline_search)
            .placeholder(R.drawable.ic_baseline_search)

        val requestBuilder = Glide.with(context)
            .load(url)

        requestBuilder
            .apply(options)
            .into(imageView)
    }
}