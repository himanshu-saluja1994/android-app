package com.example.himanshusaluja.github.vm

import android.arch.lifecycle.*
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.example.himanshusaluja.github.model.PRItem
import io.reactivex.disposables.CompositeDisposable
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.LiveData
import com.example.himanshusaluja.github.IPrVm
import com.example.himanshusaluja.github.pagination.PrDataSourceFactory
import com.example.himanshusaluja.github.network.GithubApiService


class PRListViewModel(val githubApiService: GithubApiService, val screen: IPrVm?) : ViewModel() {

    lateinit var list: LiveData<PagedList<PRItem>>
    val compositeDisposable = CompositeDisposable()
    lateinit var prFactory: PrDataSourceFactory
    var progressLoadStatus = MutableLiveData<Boolean>()

    val owner: MutableLiveData<String> = MutableLiveData()
    val repo: MutableLiveData<String> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    var onSearchString = {
        prFactory = PrDataSourceFactory(githubApiService, owner.value, repo.value, progressLoadStatus)
        val pagedListConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(10).build()
        list = LivePagedListBuilder<Int, PRItem>(prFactory, pagedListConfig)
                .build()
        screen?.loadListView()
    }
}

class PRListViewModelFactory(
        val githubApiService: GithubApiService,
        val screen: IPrVm?
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PRListViewModel(githubApiService, screen) as T
    }
}