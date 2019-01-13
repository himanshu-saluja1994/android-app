package com.example.himanshusaluja.github.pagination

import android.arch.paging.DataSource
import com.example.himanshusaluja.github.model.PRItem
import android.arch.lifecycle.MutableLiveData
import com.example.himanshusaluja.github.network.GithubApiService


class PrDataSourceFactory(val githubApiService: GithubApiService,
                          val owner: String?,
                          val repo: String?,
                          var progress:MutableLiveData<Boolean>)
    : DataSource.Factory<Int, PRItem>() {

    val liveData: MutableLiveData<PRListSourceClass> = MutableLiveData()

    override fun create(): DataSource<Int, PRItem> {
        val dataSourceClass = PRListSourceClass(githubApiService, owner, repo, progress)
        liveData.postValue(dataSourceClass)
        return dataSourceClass
    }

}