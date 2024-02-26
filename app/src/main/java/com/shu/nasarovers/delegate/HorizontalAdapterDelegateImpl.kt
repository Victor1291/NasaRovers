package com.shu.nasarovers.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.shu.nasarovers.R
import com.shu.nasarovers.databinding.ItemHorizontalBinding
import com.shu.nasarovers.models.Photos


class HorizontalAdapterDelegateImpl : HorizontalAdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup): HorizontalViewHolder {
        return HorizontalViewHolder(
            ItemHorizontalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HorizontalViewHolder, data: Any, size : Int, countPosition : Int) {

        val item = data as Photos
        // Bind data to the horizontal view holder

        with(holder.binding) {
            //обновляем tag используемый в OnClick .Складываем текущий элемент в списке.
            poster.tag = item
          /*  mMenus.tag = item

            camera.text = item?.camera?.name
            rover.text = item?.rover?.name
            dataView.text = item?.earthDate ?: ""
            sol.text = item?.sol.toString()*/
            sol.text = item?.sol.toString()
            solCount.text = size.toString()
            count.text = countPosition.toString()
            item?.let {
                com.bumptech.glide.Glide
                    .with(poster.context)
                    .load(item.imgSrc)
                    .transition(com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade())
                    .transform(
                        CenterCrop(),
                        RoundedCorners(10)
                    )
                    .placeholder(R.drawable.bg_item_placeholder)
                    .into(poster)
            }
        }


    }

    override fun getItemViewType(data: Any): Int {
        return R.layout.item_horizontal
    }

    // Horizontal ViewHolder

}
class HorizontalViewHolder(val binding: ItemHorizontalBinding) :
    RecyclerView.ViewHolder(binding.root) {
}