package com.luthtan.masak_apa_android.feature.detail

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.luthtan.masak_apa_android.R
import com.luthtan.masak_apa_android.base.BaseFragment
import com.luthtan.masak_apa_android.data.dtos.SortByModel
import com.luthtan.masak_apa_android.databinding.FragmentDetailBinding
import com.luthtan.masak_apa_android.domain.subscriber.ResultState
import com.luthtan.masak_apa_android.feature.home.FilterCategoriesAdapter
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped


@FragmentScoped
@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>(){

    override val viewModel: DetailViewModel by viewModels()

    override val binding: FragmentDetailBinding by lazy {
        FragmentDetailBinding.inflate(layoutInflater)
    }

    private val filterAdapter: FilterCategoriesAdapter by lazy {
        FilterCategoriesAdapter()
    }

    private val args: DetailFragmentArgs by navArgs()

    override fun onInitViews() {
        binding.lifecycleOwner = this

        binding.backBtn.setOnClickListener {
            onBackPressed()
        }

        with(binding.rvIngredient) {
            adapter = filterAdapter
            setHasFixedSize(true)
        }

        windowProperties(true)
    }

    override fun onInitObservers() {
        viewModel.getDetailById(args.id)

        viewModel.detailMeals.observe(this) {
            when(it) {
                is ResultState.Loading -> {

                }
                is ResultState.Success -> {
                    binding.model = it.data?.body()?.meals?.get(0)!!
                    val ingred = mutableListOf<SortByModel>()
                    it.data.body()?.meals?.get(0)!!.let { data ->
                        if (!data.strIngredient1.isNullOrBlank()) {
                            ingred.add(SortByModel(data.strIngredient1))
                        }
                        if (!data.strIngredient2.isNullOrBlank()) {
                            ingred.add(SortByModel(data.strIngredient2))
                        }
                        if (!data.strIngredient3.isNullOrBlank()) {
                            ingred.add(SortByModel(data.strIngredient3))
                        }
                        if (!data.strIngredient4.isNullOrBlank()) {
                            ingred.add(SortByModel(data.strIngredient4))
                        }
                        if (!data.strIngredient5.isNullOrBlank()) {
                            ingred.add(SortByModel(data.strIngredient5))
                        }
                        if (!data.strIngredient6.isNullOrBlank()) {
                            ingred.add(SortByModel(data.strIngredient6))
                        }
                        if (!data.strIngredient7.isNullOrBlank()) {
                            ingred.add(SortByModel(data.strIngredient7))
                        }
                        if (!data.strIngredient8.isNullOrBlank()) {
                            ingred.add(SortByModel(data.strIngredient8))
                        }
                        if (!data.strIngredient9.isNullOrBlank()) {
                            ingred.add(SortByModel(data.strIngredient9))
                        }
                        if (!data.strIngredient10.isNullOrBlank()) {
                            ingred.add(SortByModel(data.strIngredient10))
                        }
                        if (!data.strIngredient11.isNullOrBlank()) {
                            ingred.add(SortByModel(data.strIngredient11))
                        }
                        if (!data.strIngredient12.isNullOrBlank()) {
                            ingred.add(SortByModel(data.strIngredient12))
                        }
                        if (!data.strIngredient13.isNullOrBlank()) {
                            ingred.add(SortByModel(data.strIngredient13))
                        }
                        if (!data.strIngredient14.isNullOrBlank()) {
                            ingred.add(SortByModel(data.strIngredient14))
                        }
                        if (!data.strIngredient15.isNullOrBlank()) {
                            ingred.add(SortByModel(data.strIngredient15))
                        }
                        if (!data.strIngredient16.isNullOrBlank()) {
                            ingred.add(SortByModel(data.strIngredient16))
                        }
                        if (!data.strIngredient17.isNullOrBlank()) {
                            ingred.add(SortByModel(data.strIngredient17))
                        }
                        if (!data.strIngredient18.isNullOrBlank()) {
                            ingred.add(SortByModel(data.strIngredient18))
                        }
                        if (!data.strIngredient19.isNullOrBlank()) {
                            ingred.add(SortByModel(data.strIngredient19))
                        }
                        if (!data.strIngredient20.isNullOrBlank()) {
                            ingred.add(SortByModel(data.strIngredient20))
                        }
                    }
                    filterAdapter.setData(ingred)
                    binding.btnVideo.setOnClickListener { _ ->
                        watchYoutubeVideo(it.data.body()?.meals?.get(0)!!.strYoutube!!)
                    }
                }
                is ResultState.Error -> {

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        windowProperties(false)
    }

    private fun watchYoutubeVideo(url: String) {
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        requireActivity().startActivity(webIntent)
    }

    private fun windowProperties(state: Boolean) {
        val window: Window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        WindowInsetsControllerCompat(requireActivity().window, requireView()).isAppearanceLightStatusBars = state
        if (state) {
            window.statusBarColor = Color.WHITE
        } else {
            window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.colorPrimary)
        }
    }
}