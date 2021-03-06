package com.example.himanshusaluja.github

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.himanshusaluja.github.databinding.ActivityMainBinding
import android.arch.paging.PagedList
import com.example.himanshusaluja.github.model.PRItem
import com.example.himanshusaluja.github.network.Repository
import com.example.himanshusaluja.github.pagination.PaginatedAdapter
import com.example.himanshusaluja.github.vm.PRListViewModel
import com.example.himanshusaluja.github.vm.PRListViewModelFactory


class MainActivity : AppCompatActivity(), IPrVm {

    var vm: PRListViewModel? = null

    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = createVM()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.vm = vm
        binding?.setLifecycleOwner(this)
        init()
    }

    override fun loadListView() {
        if (isFinishing) {
            return
        }
        val adapter = PaginatedAdapter()
        binding?.list?.adapter = adapter

        vm?.list?.observe(this, object : Observer<PagedList<PRItem>> {
            override fun onChanged(t: PagedList<PRItem>?) {
                adapter.submitList(vm?.list?.value)
            }
        })
    }

    fun init() {
        binding?.list?.layoutManager = LinearLayoutManager(this)
    }

    fun createVM(): PRListViewModel {
        return ViewModelProviders.of(this, PRListViewModelFactory(Repository.githubApiService, this))
                .get(PRListViewModel::class.java)
    }

}
