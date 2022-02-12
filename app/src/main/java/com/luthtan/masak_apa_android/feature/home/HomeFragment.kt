package com.luthtan.masak_apa_android.feature.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.luthtan.masak_apa_android.base.BaseFragment
import com.luthtan.masak_apa_android.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@FragmentScoped
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModels()

    override val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onInitViews() {
        binding.lifecycleOwner = this
        binding.listener = viewModel
    }

    override fun onInitObservers() {
        viewModel.goToProfile.observe(this) {
            it.getContentIfNotHandled()?.let {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProfileFragment())
            }
        }
    }
}