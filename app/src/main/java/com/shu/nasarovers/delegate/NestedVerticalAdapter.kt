package com.shu.nasarovers.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shu.nasarovers.models.Photos

class NestedVerticalAdapter (
    private val verticalDelegate: VerticalAdapterDelegate
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data: List<List<Photos>> = emptyList()

    fun setData(data: List<List<Photos>>) {

        this.data = data
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return verticalDelegate.onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val horizontalData = data[position]
        verticalDelegate.onBindViewHolder(holder,horizontalData)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        val horizontalData = data[position]
        return verticalDelegate.getItemViewType(horizontalData)
    }
}