package com.hamy.kwansoassiignment.utills

import androidx.recyclerview.widget.DiffUtil
import com.hamy.kwansoassiignment.model.Grocery

open class DiffUtills(
    private val olditem: List<Grocery>,
    private val newItem: List<Grocery>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return olditem.size
    }

    override fun getNewListSize(): Int {
        return newItem.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // In the real world you need to compare something unique like id
        return olditem[oldItemPosition] == newItem[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // This is called if areItemsTheSame() == true;
        return olditem[oldItemPosition] == newItem[newItemPosition]
    }
}
//end