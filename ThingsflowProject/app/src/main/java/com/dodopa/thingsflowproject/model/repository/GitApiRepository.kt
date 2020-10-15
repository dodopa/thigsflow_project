package com.dodopa.thingsflowproject.model.repository

import com.dodopa.thingsflowproject.model.api.GitApi
import com.dodopa.thingsflowproject.model.entity.Issue
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

object GitApiRepository {

    fun getIssueListFromRepo(org: String, repo: String): Single<List<Issue>> {
        return GitApi.getIssueListFromRepo(org, repo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}