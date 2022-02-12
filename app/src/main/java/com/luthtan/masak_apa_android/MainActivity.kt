package com.luthtan.masak_apa_android

import androidx.activity.viewModels
import com.luthtan.masak_apa_android.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : com.luthtan.masak_apa_android.base.BaseActivity<ActivityMainBinding, com.luthtan.masak_apa_android.MainViewModel>() {

    override val viewModel: com.luthtan.masak_apa_android.MainViewModel by viewModels()

    override val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onInitViews() {

    }

    override fun onInitObservers() {

    }
}