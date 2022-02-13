package com.luthtan.masak_apa_android.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

@SuppressLint("NotifyDataSetChanged")
abstract class BaseAdapter<VB : ViewBinding, DATA>(
    private val inflate: Inflate<VB>,
) : RecyclerView.Adapter<BaseAdapter<VB, DATA>.BaseViewHolder>() {

    private val data: MutableList<DATA> = mutableListOf()

    open fun setData(data: List<DATA>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    abstract fun bind(binding: VB, data: DATA?)

    inner class BaseViewHolder(private val binding: VB) : RecyclerView.ViewHolder(binding.root) {
        fun getBinding(): VB = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = inflate.invoke(LayoutInflater.from(parent.context), parent, false)
        return BaseViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when(data.isNotEmpty()) {
            true -> bind(holder.getBinding(), data = data[position])
            false -> bind(holder.getBinding(), data = null)
        }
    }
}