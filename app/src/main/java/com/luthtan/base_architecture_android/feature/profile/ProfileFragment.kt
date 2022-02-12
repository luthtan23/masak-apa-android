package com.luthtan.base_architecture_android.feature.profile

import androidx.fragment.app.viewModels
import com.luthtan.base_architecture_android.base.BaseFragment
import com.luthtan.base_architecture_android.data.db.ProfileModel
import com.luthtan.base_architecture_android.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@FragmentScoped
@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {

    private val profileAdapter: ProfileAdapter by lazy {
        ProfileAdapter()
    }

    override val viewModel: ProfileViewModel by viewModels()

    override val binding: FragmentProfileBinding by lazy {
        FragmentProfileBinding.inflate(layoutInflater)
    }

    override fun onInitObservers() {
        viewModel.getDataCoroutine()
        viewModel.getDataRxJava()
    }

    override fun onInitViews() {
        val listData = mutableListOf(ProfileModel("1"), ProfileModel("2"), ProfileModel("3"))
        profileAdapter.setData(listData)
        binding.rvTest.adapter = profileAdapter

        viewModel.showToast.observe(this) {
            it.getContentIfNotHandled()?.let { msg ->
                showToast(msg)
            }
        }
    }
}