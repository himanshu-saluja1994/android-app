package com.example.himanshusaluja.github.pagination

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.databinding.DataBindingUtil
import android.view.ViewGroup
import android.arch.paging.PagedListAdapter
import com.example.himanshusaluja.github.R
import com.example.himanshusaluja.github.databinding.ItemPrBinding
import com.example.himanshusaluja.github.model.PRItem


class PaginatedAdapter internal constructor() : PagedListAdapter<PRItem, PaginatedAdapter.ItemViewHolder>(PRItem.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val binding: ItemPrBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_pr, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.vm = getItem(position)
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    inner class ItemViewHolder(var binding: ItemPrBinding) : RecyclerView.ViewHolder(binding.getRoot())
}