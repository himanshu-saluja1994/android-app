package com.example.himanshusaluja.github.model

import com.google.gson.annotations.SerializedName
import android.support.annotation.NonNull
import android.support.v7.util.DiffUtil


class PRItem() {
    @SerializedName("number")
    var number: Int = 0
    @SerializedName("url")
    var url: String? = ""
    @SerializedName("state")
    var state: String? = ""

    companion object {
        var DIFF_CALLBACK: DiffUtil.ItemCallback<PRItem> = object : DiffUtil.ItemCallback<PRItem>() {
            override fun areItemsTheSame(oldItem: PRItem, newItem: PRItem): Boolean {
                return oldItem.number.equals(newItem.number)
            }

            override fun areContentsTheSame(oldItem: PRItem, newItem: PRItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other == this)
            return true
        if (other is PRItem) {
            return other.number.equals(this.number)
        }
        return false
    }
}
