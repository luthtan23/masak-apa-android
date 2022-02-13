package com.luthtan.masak_apa_android.feature.filter_categories

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.luthtan.masak_apa_android.base.BaseFragment
import com.luthtan.masak_apa_android.base.util.ShimmerData
import com.luthtan.masak_apa_android.data.dtos.MealsItemModel
import com.luthtan.masak_apa_android.databinding.FragmentMealByCategoriesBinding
import com.luthtan.masak_apa_android.domain.subscriber.ResultState
import com.luthtan.masak_apa_android.feature.common.OnCustomClickListener
import com.luthtan.masak_apa_android.feature.common.cutom_ui.SearchBarWithVoice
import com.luthtan.masak_apa_android.feature.home.MealsAdapter
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@FragmentScoped
@AndroidEntryPoint
class MealByCategoriesFragment : BaseFragment<FragmentMealByCategoriesBinding, MealByCategoriesViewModel>(){

    override val viewModel: MealByCategoriesViewModel by viewModels()

    override val binding: FragmentMealByCategoriesBinding by lazy {
        FragmentMealByCategoriesBinding.inflate(layoutInflater)
    }

    private val mealsAdapter: MealsAdapter by lazy {
        MealsAdapter()
    }

    private val args: MealByCategoriesFragmentArgs by navArgs()

    override fun onInitViews() {
        binding.lifecycleOwner = this
        binding.listener = viewModel
        binding.errorListener = viewModel

        with(binding.rvMealsByCategories) {
            adapter = mealsAdapter
            setHasFixedSize(true)
        }

        viewModel.refresh.observe(this) {
            it.getContentIfNotHandled().let {
                viewModel.getMeals(args.meals)
            }
        }

        viewModel.backPressed.observe(this) {
            it.getContentIfNotHandled()?.let {
                onBackPressed()
            }
        }

        binding.search.registerSearchBar(this, object : SearchBarWithVoice.OnSearchListener {
            override fun onSearch(query: String) {
                viewModel.initQuerySearchMeals(query)
            }
        })

        mealsAdapter.setCustomClickListener(object : OnCustomClickListener {
            override fun onClick(mealsModel: MealsItemModel) {
                super.onClick(mealsModel)
                findNavController().navigate(MealByCategoriesFragmentDirections.actionMealByCategoriesFragmentToDetailFragment(mealsModel.idMeal!!))
            }
        })
    }

    override fun onInitObservers() {
        viewModel.getMeals(args.meals)

        initMeals()
        setSearchByName()
    }

    private fun initMeals() {
        viewModel.mealsModel.observe(this) {
            when(it) {
                is ResultState.Loading -> {
                    mealsAdapter.setData(ShimmerData.getShimmerMeals())
                    with(binding) {
                        rvMealsByCategories.visibility = View.VISIBLE
                        emptyLyt.emptyParent.visibility = View.GONE
                        errorLyt.errorParent.visibility = View.GONE
                    }
                }
                is ResultState.Success -> {
                    if (it.data?.body()!!.meals!!.isNotEmpty()) {
                        mealsAdapter.setData(it.data.body()!!.meals!!)
                    } else {
                        with(binding) {
                            rvMealsByCategories.visibility = View.GONE
                            errorLyt.errorParent.visibility = View.GONE
                            emptyLyt.emptyParent.visibility = View.VISIBLE
                        }
                    }
                }
                is ResultState.Error -> {
                    with(binding) {
                        rvMealsByCategories.visibility = View.GONE
                        emptyLyt.emptyParent.visibility = View.GONE
                        errorLyt.errorParent.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun setSearchByName() {
        viewModel.searchName.observe(this) {
            when(it) {
                is ResultState.Loading -> {
                    mealsAdapter.setData(ShimmerData.getShimmerMeals())
                }
                is ResultState.Success -> {
                    if (it.data?.body()!!.meals!!.isNotEmpty()) {
                        val temp = mutableListOf<MealsItemModel>()
                        it.data.body()!!.meals?.forEach { mealsItem ->
                            temp.add(
                                MealsItemModel(
                                    mealsItem?.strMealThumb,
                                    mealsItem?.idMeal,
                                    mealsItem?.strMeal)
                            )
                        }
                        mealsAdapter.setData(temp)
                    } else {
                        with(binding) {
                            rvMealsByCategories.visibility = View.GONE
                            errorLyt.errorParent.visibility = View.GONE
                            emptyLyt.emptyParent.visibility = View.VISIBLE
                        }
                    }
                }
                is ResultState.Error -> {
                    with(binding) {
                        rvMealsByCategories.visibility = View.GONE
                        emptyLyt.emptyParent.visibility = View.GONE
                        errorLyt.errorParent.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}