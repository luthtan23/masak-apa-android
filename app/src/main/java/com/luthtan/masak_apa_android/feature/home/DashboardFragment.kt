package com.luthtan.masak_apa_android.feature.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.luthtan.masak_apa_android.base.BaseFragment
import com.luthtan.masak_apa_android.base.util.ShimmerData
import com.luthtan.masak_apa_android.data.dtos.CategoriesItem
import com.luthtan.masak_apa_android.data.dtos.MealsItemModel
import com.luthtan.masak_apa_android.data.dtos.SortByModel
import com.luthtan.masak_apa_android.databinding.FragmentDashboardBinding
import com.luthtan.masak_apa_android.domain.subscriber.ResultState
import com.luthtan.masak_apa_android.feature.common.OnCustomClickListener
import com.luthtan.masak_apa_android.feature.common.cutom_ui.SearchBarWithVoice
import com.luthtan.masak_apa_android.feature.enum.DashboardSortBy
import com.luthtan.masak_apa_android.feature.home.sort.BottomSheetSortByFilterFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@FragmentScoped
@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>() {

    override val viewModel: DashboardViewModel by viewModels()

    private val dashboardAdapter: DashboardAdapter by lazy {
        DashboardAdapter()
    }

    private val mealsAdapter: MealsAdapter by lazy {
        MealsAdapter()
    }

    override val binding: FragmentDashboardBinding by lazy {
        FragmentDashboardBinding.inflate(layoutInflater)
    }

    private val bottomSheetSortByFilterFragment by lazy {
        BottomSheetSortByFilterFragment()
    }

    private val filterCategoriesAdapter: FilterCategoriesAdapter by lazy {
        FilterCategoriesAdapter()
    }

    private var identify: DashboardSortBy = DashboardSortBy.ALL_CATEGORIES

    override fun onInitViews() {
        binding.lifecycleOwner = this
        binding.listener = viewModel
        binding.errorListener = viewModel

        with(binding.rvCategories){
            adapter = dashboardAdapter
            setHasFixedSize(true)
        }

        with(binding.rvCategoriesFilter) {
            adapter = filterCategoriesAdapter
            setHasFixedSize(true)
        }

        with(binding.rvMealsByCategories) {
            adapter = mealsAdapter
            setHasFixedSize(true)
        }

        binding.search.registerSearchBar(this, object : SearchBarWithVoice.OnSearchListener {
            override fun onSearch(query: String) {
                when(identify) {
                    DashboardSortBy.ALL_CATEGORIES -> viewModel.initQuerySearchCategories(query)
                    DashboardSortBy.BY_CATEGORIES -> viewModel.initQuerySearchMeals(query)
                }
            }
        })

        dashboardAdapter.setCustomClickListener(object : OnCustomClickListener {
            override fun onClick(categoriesItem: CategoriesItem) {
                super.onClick(categoriesItem)
                findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToMealByCategoriesFragment(categoriesItem.strCategory!!))
            }
        })

        filterCategoriesAdapter.setCustomClickListener(object : OnCustomClickListener {
            override fun onClick(sortByModel: SortByModel) {
                super.onClick(sortByModel)
                viewModel.tagFilterSelected(sortByModel)
                viewModel.getMeals(sortByModel.title)
            }
        })

        mealsAdapter.setCustomClickListener(object : OnCustomClickListener {
            override fun onClick(mealsModel: MealsItemModel) {
                super.onClick(mealsModel)
                findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToDetailFragment(mealsModel.idMeal!!))
            }
        })

        binding.btnSort.setOnClickListener {
            val arg = Bundle()
            arg.putParcelable("sortBy", viewModel.selectedSortBy.value)
            bottomSheetSortByFilterFragment.arguments = arg
            bottomSheetSortByFilterFragment.show(childFragmentManager, "sortBy") {
                viewModel.sortByModel(it)
            }
        }
    }

    override fun onInitObservers() {

        initCategories()
        initMeals()
        initFilter()
        setSearchByName()

        viewModel.goToProfile.observe(this) {
            it.getContentIfNotHandled()?.let {
                findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToProfileFragment())
            }
        }

        viewModel.refresh.observe(this) {
            it.getContentIfNotHandled().let {
                viewModel.getDataCoroutine()
            }
        }

    }

    private fun initCategories() {
        viewModel.categoriesData.observe(this) {
            when(it) {
                is ResultState.Loading -> {
                    with(binding) {
                        rvCategories.visibility = View.VISIBLE
                        errorLyt.errorParent.visibility = View.GONE
                        emptyLyt.emptyParent.visibility = View.GONE
                        rvMealsByCategories.visibility = View.GONE
                    }
                    dashboardAdapter.setData(ShimmerData.getShimmerCategories())
                }
                is ResultState.Success -> {
                    if (it.data?.body()!!.categories!!.isNotEmpty()) {
                        dashboardAdapter.setData(it.data.body()!!.categories!!)
                    } else {
                        with(binding) {
                            rvCategories.visibility = View.GONE
                            errorLyt.errorParent.visibility = View.GONE
                            emptyLyt.emptyParent.visibility = View.VISIBLE
                        }
                    }
                }
                is ResultState.Error -> {
                    with(binding) {
                        rvMealsByCategories.visibility = View.GONE
                        rvCategories.visibility = View.GONE
                        emptyLyt.emptyParent.visibility = View.GONE
                        errorLyt.errorParent.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun initMeals() {
        viewModel.mealsModel.observe(this) {
            when(it) {
                is ResultState.Loading -> {
                    mealsAdapter.setData(ShimmerData.getShimmerMeals())
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
                        rvCategories.visibility = View.GONE
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
                            viewModel.tagFilterSelected(SortByModel(
                                title = mealsItem?.strCategory!!,
                                selected = true
                            ))
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
                        rvCategories.visibility = View.GONE
                        emptyLyt.emptyParent.visibility = View.GONE
                        errorLyt.errorParent.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun initFilter() {
        viewModel.selectedSortBy.observe(this) {
            binding.search.clearText()
            if (it.title == "Show all Categories") {
                binding.search.setHint("Search Categories")
                identify = DashboardSortBy.ALL_CATEGORIES
                with(binding) {
                    rvCategories.visibility = View.VISIBLE
                    rvCategoriesFilter.visibility = View.GONE
                    rvMealsByCategories.visibility = View.GONE
                }
            } else {
                binding.search.setHint("Search Meals")
                identify = DashboardSortBy.BY_CATEGORIES
                with(binding) {
                    rvCategories.visibility = View.GONE
                    rvCategoriesFilter.visibility = View.VISIBLE
                    rvMealsByCategories.visibility = View.VISIBLE
                }
            }
        }

        viewModel.categoriesDataTag.observe(this) {
            filterCategoriesAdapter.setData(it)
        }
    }
}