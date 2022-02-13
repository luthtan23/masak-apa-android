package com.luthtan.masak_apa_android.feature.common.cutom_ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.BindingAdapter
import com.luthtan.masak_apa_android.databinding.ViewHeaderBottomSheetBinding

/**
 * Created by Abraham Lay on 12/06/20.
 */
class HeaderBottomSheet @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var binding: ViewHeaderBottomSheetBinding =
        ViewHeaderBottomSheetBinding.inflate(LayoutInflater.from(context), this, true)

    private var l: OnClickListener? = null
    private var listener: OnCloseClickListener? = null
    private var listenerAction: OnActionClickListener? = null
    private var listenerAction2: OnActionClickListener? = null

    init {

        binding.btnCloseBottomSheet.setOnClickListener {
            listener?.onCloseClicked()
        }

        binding.btnAction.setOnClickListener {
            listenerAction?.onActionClicked()
        }

        binding.btnAction2.setOnClickListener {
            listenerAction2?.onActionClicked()
        }
    }

    fun setTitle(text: String) {
        binding.txtTitle.text = text
    }

    fun setTitle(text: Int) {
        binding.txtTitle.text = context.getString(text)
    }

    fun getTitle(): String {
        return binding.txtTitle.text.toString()
    }

    fun showCloseButton() {
        binding.btnCloseBottomSheet.visibility = VISIBLE
    }

    fun invisibleCloseButton() {
        binding.btnCloseBottomSheet.visibility = INVISIBLE
    }

    fun hideCloseButton() {
        binding.btnCloseBottomSheet.visibility = GONE
    }

    fun setIconCloseButton(icon: Drawable) {
        binding.btnCloseBottomSheet.setImageDrawable(icon)
    }

    fun showActionButton() {
        binding.btnAction.visibility = VISIBLE
    }

    fun hideActionButton() {
        binding.btnAction.visibility = GONE
    }

    fun showActionButton2() {
        binding.btnAction2.visibility = VISIBLE
    }

    fun hideActionButton2() {
        binding.btnAction2.visibility = GONE
    }

    fun setIconBtnAction(icon: Drawable) {
        binding.btnAction.setImageDrawable(icon)
    }

    fun setIconBtnAction2(icon: Drawable) {
        binding.btnAction2.setImageDrawable(icon)
    }

    interface OnCloseClickListener {
        fun onCloseClicked()
    }

    interface OnActionClickListener {
        fun onActionClicked()
    }

    fun setOnCloseClickListener(listener: OnCloseClickListener) {
        this.listener = listener
    }

    fun setOnActionClickListener(listener: OnActionClickListener) {
        this.listenerAction = listener
    }

    fun setOnActionClickListener2(listener2: OnActionClickListener) {
        this.listenerAction2 = listener2
    }

    fun hideSwipeDownView() {
        binding.swipeDownView.visibility = INVISIBLE
    }

    fun showSwipeDownView() {
        binding.swipeDownView.visibility = VISIBLE
    }
}

object HeaderBottomSheetBindingAdapters {
    @BindingAdapter("hbs_invisibleButton")
    @JvmStatic
    fun HeaderBottomSheet.bindInvisibleButton(hbs_invisibleButton: Boolean) {
        if (hbs_invisibleButton) {
            this.invisibleCloseButton()
        } else {
            this.hideCloseButton()
        }
    }

    @BindingAdapter("hbs_showCloseButton")
    @JvmStatic
    fun HeaderBottomSheet.bindShowCloseButton(hbs_showCloseButton: Boolean) {
        if (hbs_showCloseButton) {
            this.showCloseButton()
        } else {
            this.hideCloseButton()
        }
    }

    @BindingAdapter("hbs_iconCloseButton")
    @JvmStatic
    fun HeaderBottomSheet.bindIconCloseButton(hbs_iconCloseButton: Drawable) {
        this.setIconCloseButton(hbs_iconCloseButton)
    }

    @BindingAdapter("hbs_showActionButton")
    @JvmStatic
    fun HeaderBottomSheet.bindShowActionButton(hbs_showActionButton: Boolean) {
        if (hbs_showActionButton) {
            this.showCloseButton()
        } else {
            this.hideCloseButton()
        }
    }

    @BindingAdapter("hbs_showActionButton2")
    @JvmStatic
    fun HeaderBottomSheet.bindShowActionButton2(hbs_showActionButton2: Boolean) {
        if (hbs_showActionButton2) {
            this.showActionButton2()
        } else {
            this.showActionButton2()
        }
    }

    @BindingAdapter("hbs_iconActionButton")
    @JvmStatic
    fun HeaderBottomSheet.bindIconActionButton(hbs_iconActionButton: Drawable) {
        this.setIconBtnAction(hbs_iconActionButton)
    }

    @BindingAdapter("hbs_iconActionButton2")
    @JvmStatic
    fun HeaderBottomSheet.bindIconActionButton2(hbs_iconActionButton2: Drawable) {
        this.setIconBtnAction2(hbs_iconActionButton2)
    }

    @BindingAdapter("hbs_title")
    @JvmStatic
    fun HeaderBottomSheet.bindTitle(hbs_title: String) {
        this.setTitle(hbs_title)
    }

    @BindingAdapter("hbs_title")
    @JvmStatic
    fun HeaderBottomSheet.bindTitle(hbs_title: Int) {
        this.setTitle(hbs_title)
    }

    @BindingAdapter("hbs_showSwipeDown")
    @JvmStatic
    fun HeaderBottomSheet.bindSwipeDownView(hbs_showSwipeDown: Boolean) {
        if (hbs_showSwipeDown) {
            this.showSwipeDownView()
        } else {
            this.hideSwipeDownView()
        }
    }
}