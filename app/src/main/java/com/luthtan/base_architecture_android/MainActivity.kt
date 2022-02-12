package com.luthtan.base_architecture_android

import androidx.activity.viewModels
import com.luthtan.base_architecture_android.base.BaseActivity
import com.luthtan.base_architecture_android.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel by viewModels()

    override val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onInitViews() {

    }

    override fun onInitObservers() {

    }
}