package com.luthtan.masak_apa_android.feature.profile

import com.luthtan.masak_apa_android.base.BaseAdapter
import com.luthtan.masak_apa_android.data.db.ProfileModel
import com.luthtan.masak_apa_android.databinding.ItemProfileBinding

class ProfileAdapter : BaseAdapter<ItemProfileBinding, ProfileModel>(
    ItemProfileBinding::inflate
) {

    override fun bind(binding: ItemProfileBinding, data: ProfileModel?) {
        if (data != null) {
            binding.model = data
        }
    }
}