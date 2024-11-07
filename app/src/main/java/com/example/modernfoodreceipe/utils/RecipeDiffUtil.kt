package com.example.modernfoodreceipe.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.modernfoodreceipe.models.data.Result

class RecipeDiffUtil(
    private val oldList:List<Result>,
    private val newList:List<Result>): DiffUtil.Callback() {


    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}