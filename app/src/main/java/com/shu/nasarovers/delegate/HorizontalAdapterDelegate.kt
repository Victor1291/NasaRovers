package com.shu.nasarovers.delegate

import android.view.ViewGroup

interface HorizontalAdapterDelegate {
    fun onCreateViewHolder(parent: ViewGroup): HorizontalViewHolder
    fun onBindViewHolder(holder: HorizontalViewHolder, data: Any, size: Int, count : Int)
    fun getItemViewType(data: Any): Int
}