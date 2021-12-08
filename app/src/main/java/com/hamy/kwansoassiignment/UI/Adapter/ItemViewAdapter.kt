package com.hamy.kwansoassiignment.UI.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hamy.kwansoassiignment.Model.Grocery
import com.hamy.kwansoassiignment.databinding.ItemViewBinding

class ItemViewAdapter(
    var itemList: List<Grocery>,
) : RecyclerView.Adapter<ItemViewAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder, {
            with(itemList[position], {
                binding.itemName.text = this.itemName
                binding.itemPrice.text = this.itemAmount.toString()
            })
        })
    }
    override fun getItemCount(): Int {
        return itemList.size
    }
}
