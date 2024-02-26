package com.shu.nasarovers.delegate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shu.nasarovers.R


class VerticalAdapterDelegateImpl (
    private val horizontalDelegate: HorizontalAdapterDelegate
) : VerticalAdapterDelegate {

    private lateinit var horizontalAdapter : HorizontalAdapter

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vertical, parent, false)
        return VerticalViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, data: List<Any>) {
        val verticalViewHolder = holder as VerticalViewHolder
        val horizontalRecyclerView = verticalViewHolder.itemView.findViewById<RecyclerView>(R.id.horizontalRecyclerView)

        horizontalRecyclerView.layoutManager = LinearLayoutManager(verticalViewHolder.itemView.context, LinearLayoutManager.HORIZONTAL, false)

        horizontalAdapter = HorizontalAdapter(horizontalDelegate)
        horizontalAdapter.setData(data)
        horizontalRecyclerView.adapter = horizontalAdapter
    }

    override fun getItemViewType(data: Any): Int {
        return R.layout.item_vertical
    }

    // Vertical ViewHolder
    class VerticalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}