package com.example.himanshusaluja.github.network

import com.example.himanshusaluja.github.model.PRItem
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {
    @GET("repos/{owner}/{repo}/pulls")
    fun getPullRequests(@Path("owner") owner: String,
                        @Path("repo") repo: String,
                        @Query("page") page: Int,
                        @Query("per_page") pageSize: Int): Single<List<PRItem>>
}