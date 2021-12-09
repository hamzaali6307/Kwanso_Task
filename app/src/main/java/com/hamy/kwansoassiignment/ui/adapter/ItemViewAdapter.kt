package com.hamy.kwansoassiignment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hamy.kwansoassiignment.model.Grocery
import com.hamy.kwansoassiignment.databinding.ItemViewBinding
import com.hamy.kwansoassiignment.utills.DiffUtills
import com.hamy.kwansoassiignment.utills.Utility

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
                binding.itemPrice.text = """${this.itemAmount} PKR"""
                binding.itemStatus.text = this.status
            })
        })
    }
    override fun getItemCount(): Int {
        return itemList.size
    }
    fun updateList(list: List<Grocery>) {
        val diffCallback = DiffUtills(this.itemList, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)
        this.itemList = list
    }
}
