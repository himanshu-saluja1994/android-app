package com.example.himanshusaluja.github.pagination

import android.arch.paging.PageKeyedDataSource
import com.example.himanshusaluja.github.model.PRItem
import android.arch.lifecycle.MutableLiveData
import com.example.himanshusaluja.github.network.GithubApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class PRListSourceClass(val githubApiService: GithubApiService,
                        val owner: String?,
                        val repo: String?, var progressLiveStatus:MutableLiveData<Boolean>)
    : PageKeyedDataSource<Int, PRItem>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, PRItem>) {
        progressLiveStatus.postValue(true)
        githubApiService.getPullRequests(owner ?: "", repo
                ?: "", 1, params.requestedLoadSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<PRItem>>() {
                    override fun onSuccess(prs: List<PRItem>) {
                        callback.onResult(prs, 1, 2)
                        progressLiveStatus.postValue(false)
                    }

                    override fun onError(e: Throwable) {
                        progressLiveStatus.postValue(false)
                    }
                })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PRItem>) {

        githubApiService.getPullRequests(owner ?: "", repo
                ?: "", params.key, params.requestedLoadSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<PRItem>>() {
                    override fun onSuccess(prs: List<PRItem>) {
                        callback.onResult(prs, params.key + 1)
                    }

                    override fun onError(e: Throwable) {
                    }
                })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PRItem>) {

    }

}