package com.luthtan.masak_apa_android.feature.profile

import android.graphics.Color
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.viewModels
import com.luthtan.masak_apa_android.R
import com.luthtan.masak_apa_android.base.BaseFragment
import com.luthtan.masak_apa_android.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@FragmentScoped
@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {

    override val viewModel: ProfileViewModel by viewModels()

    override val binding: FragmentProfileBinding by lazy {
        FragmentProfileBinding.inflate(layoutInflater)
    }

    override fun onInitObservers() {

    }

    override fun onInitViews() {
        windowProperties(true)

        binding.backBtn.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        windowProperties(false)
    }

    private fun windowProperties(state: Boolean) {
        val window: Window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        WindowInsetsControllerCompat(
            requireActivity().window,
            requireView()
        ).isAppearanceLightStatusBars = state
        if (state) {
            window.statusBarColor = Color.WHITE
        } else {
            window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.colorPrimary)
        }
    }
}