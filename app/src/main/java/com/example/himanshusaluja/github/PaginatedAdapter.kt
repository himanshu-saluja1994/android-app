package com.example.himanshusaluja.github

import android.support.v7.widget.RecyclerView
import android.support.annotation.NonNull
import android.view.LayoutInflater
import android.databinding.DataBindingUtil
import android.view.ViewGroup
import android.arch.paging.PagedListAdapter
import com.example.himanshusaluja.github.databinding.ItemPrBinding
import com.example.himanshusaluja.github.model.PRItem


class PaginatedAdapter internal constructor() : PagedListAdapter<PRItem, PaginatedAdapter.MyViewHolder>(PRItem.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val binding: ItemPrBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_pr, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.vm = getItem(position)
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    inner class MyViewHolder(var binding: ItemPrBinding) : RecyclerView.ViewHolder(binding.getRoot())
}