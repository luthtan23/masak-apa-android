package com.luthtan.base_architecture_android.base

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.luthtan.base_architecture_android.R

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

@SuppressLint("Registered")
abstract class BaseBottomSheetFragment<VB : ViewBinding,VM : BaseViewModel>(
    private val inflate: Inflate<VB>
) : BottomSheetDialogFragment() {

    abstract val viewModel: VM

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)

        dialog?.setOnShowListener {
            if (fullScreen()) {
                setupFullHeight(dialog as BottomSheetDialog)
            } else {
                val d = dialog as BottomSheetDialog
                val bottomSheet = d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
                val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        if (disableSwipeDown()) {
            setupBottomSheetBehaviour()
        }

        isCancelable = cancelAble()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onInitViews()
        onInitObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun dismissDialog(){
        dialog?.cancel()
    }

    abstract fun disableSwipeDown():Boolean
    abstract fun cancelAble():Boolean
    abstract fun fullScreen():Boolean

    private fun setupFullHeight(bottomSheetDialog: BottomSheetDialog) {
        val parentLayout =
            bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
        parentLayout?.let { it ->
            val behaviour = BottomSheetBehavior.from(it)
            val layoutParams = it.layoutParams
            layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
            it.layoutParams = layoutParams
            behaviour.state = BottomSheetBehavior.STATE_EXPANDED
            behaviour.skipCollapsed = true
        }
    }

    fun showToast(text:String){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    fun setupBottomSheetBehaviour() {
        if (dialog is BottomSheetDialog) {
            val behaviour = (dialog as BottomSheetDialog).behavior
            behaviour.isDraggable = false
        }
    }

    protected open fun onInitViews() = Unit

    protected open fun onInitObservers() = Unit

    protected val permissionRequestLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        val granted = permissions.entries.all {
            it.value == true
        }
        onRequestResult(granted)
    }

    protected val requestMultipleFileLauncher = registerForActivityResult(ActivityResultContracts.OpenMultipleDocuments()) { result ->
        onMultipleFileSelected(result)
    }

    protected open fun onMultipleFileSelected(list: MutableList<Uri>) = Unit
    protected open fun onRequestResult(granted: Boolean) = Unit
}