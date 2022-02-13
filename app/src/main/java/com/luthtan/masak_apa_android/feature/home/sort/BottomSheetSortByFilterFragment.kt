package com.luthtan.masak_apa_android.feature.home.sort

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.luthtan.masak_apa_android.base.BaseBottomSheetFragment
import com.luthtan.masak_apa_android.data.dtos.SortByModel
import com.luthtan.masak_apa_android.databinding.BottomSheetSortBinding
import com.luthtan.masak_apa_android.feature.common.OnSortByListener

class BottomSheetSortByFilterFragment :
    BaseBottomSheetFragment<BottomSheetSortBinding, BottomSheetSortByFilterViewModel>(
        BottomSheetSortBinding::inflate
    ) {

    override val viewModel: BottomSheetSortByFilterViewModel by viewModels()

    override fun disableSwipeDown(): Boolean = false

    override fun cancelAble(): Boolean = true

    override fun fullScreen(): Boolean = false

    private lateinit var sortCallBack: (model: SortByModel) -> Unit

    private val bottomSheetSortByFilterAdapter: BottomSheetSortByFilterAdapter by lazy {
        BottomSheetSortByFilterAdapter()
    }

    override fun onInitViews() {
        super.onInitViews()

        binding.lifecycleOwner = this

        initRecyclerView()

        viewModel.showToast.observe(this) {
            it.getContentIfNotHandled()?.let { message ->
                showToast(message)
            }
        }

        arguments?.let {
            viewModel.initData(
                it.getParcelable("sortBy") ?: SortByModel(),
            )
        }
    }

    fun show(manager: FragmentManager, tag: String?, callback: (model: SortByModel) -> Unit) {
        sortCallBack = callback
        val ft = manager.beginTransaction()
        ft.setReorderingAllowed(true)
        ft.add(this, tag)
        ft.commit()
    }

    private fun initRecyclerView() {
        with(binding.recyclerViewSortBy) {
            adapter = bottomSheetSortByFilterAdapter
            setHasFixedSize(true)
        }
        bottomSheetSortByFilterAdapter.setCustomOnClick(object : OnSortByListener {
            override fun onClick(sortByModel: SortByModel) {
                viewModel.initData(sortByModel)
                if (::sortCallBack.isInitialized) {
                    sortCallBack.invoke(sortByModel)
                }
            }
        })

        viewModel.modelSortBy.observe(this) {
            bottomSheetSortByFilterAdapter.setData(it)
        }
    }

}