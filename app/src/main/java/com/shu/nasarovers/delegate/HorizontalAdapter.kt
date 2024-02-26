package com.shu.nasarovers.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.shu.nasarovers.models.Photos

class HorizontalAdapter (
    private val horizontalDelegate: HorizontalAdapterDelegate
) : ListAdapter<Photos, HorizontalViewHolder>(DiffUtilCallback()) {

    private var data: List<Any> = emptyList()

    fun setData(data: List<Any>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalViewHolder {
        return horizontalDelegate.onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: HorizontalViewHolder, position: Int) {
        val item = data[position]
        horizontalDelegate.onBindViewHolder(holder, item,data.size,position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        val item = data[position]
        return horizontalDelegate.getItemViewType(item)
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<Photos>() {
    override fun areItemsTheSame(oldItem: Photos, newItem: Photos): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Photos, newItem: Photos): Boolean = oldItem == newItem
}