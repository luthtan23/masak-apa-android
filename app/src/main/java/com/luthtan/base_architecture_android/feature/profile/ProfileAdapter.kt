package com.luthtan.base_architecture_android.feature.profile

import com.luthtan.base_architecture_android.base.BaseAdapter
import com.luthtan.base_architecture_android.data.db.ProfileModel
import com.luthtan.base_architecture_android.databinding.ItemProfileBinding

class ProfileAdapter : BaseAdapter<ItemProfileBinding, ProfileModel>(
    ItemProfileBinding::inflate,
) {

    override fun bind(binding: ItemProfileBinding, data: ProfileModel?) {
        if (data != null) {
            binding.model = data
        }
    }
}