package com.dodopa.thingsflowproject.model.service

import com.dodopa.thingsflowproject.model.entity.Issue
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitApiService {
    @GET("/repos/{org}/{repo}/issues")
    fun getIssueListFromRepo(
        @Path("org") org: String,
        @Path("repo") repo: String
    ): Single<List<Issue>>

    @GET("/repos/{org}/{repo}/issues/{issueNumber}")
    fun searchIssueByIssueNumber(
        @Path("org") org: String,
        @Path("repo") repo: String,
        @Path("issueNumber") issueNumber: Int
    ): Single<Issue>
}