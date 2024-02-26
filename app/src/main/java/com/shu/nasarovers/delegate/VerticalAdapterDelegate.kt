package com.shu.nasarovers.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface VerticalAdapterDelegate {
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    fun onBindViewHolder(holder: RecyclerView.ViewHolder, data: List<Any>)
    fun getItemViewType(data: Any): Int
}