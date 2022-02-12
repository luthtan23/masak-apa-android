package com.luthtan.base_architecture_android.feature.common.cutom_ui

import android.content.Context
import android.util.AttributeSet
import android.view.WindowInsets
import androidx.constraintlayout.widget.ConstraintLayout

class ConstraintLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    override fun onApplyWindowInsets(insets: WindowInsets?): WindowInsets {
        val childCount = childCount
        for (index in 0 until childCount) getChildAt(index).dispatchApplyWindowInsets(insets) // let children know about WindowInsets
        return insets!!
    }
}