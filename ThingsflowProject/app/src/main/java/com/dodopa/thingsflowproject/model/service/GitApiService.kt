package com.dodopa.thingsflowproject.model.service

import com.dodopa.thingsflowproject.model.entity.Issue
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GitApiService {
    @GET("/repos/{org}/{repo}/issues")
    fun getIssueListFromRepo(
        @Path("org") org: String,
        @Path("repo") repo: String
    ): Single<List<Issue>>
}