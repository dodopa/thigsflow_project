package com.dodopa.thingsflowproject.model.api

import com.dodopa.thingsflowproject.RetrofitCreator
import com.dodopa.thingsflowproject.model.service.GitApiService

class GitApi {

    companion object {
        private const val baseUrl = "https://api.github.com"

        fun getIssueListFromRepo(org: String, repo: String) =
            RetrofitCreator.create(baseUrl, GitApiService::class.java)
                .getIssueListFromRepo(org, repo)
    }
}