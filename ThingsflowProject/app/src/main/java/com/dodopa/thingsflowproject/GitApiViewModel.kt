package com.dodopa.thingsflowproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dodopa.thingsflowproject.model.entity.Issue
import com.dodopa.thingsflowproject.model.repository.GitApiRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable

class GitApiViewModel : ViewModel() {

    private val disposables = CompositeDisposable()

    private var _fetchedIssueList = MutableLiveData<List<Issue>>()
    val fetchedIssueList: LiveData<List<Issue>>
        get() = _fetchedIssueList

    private var _searchedIssue = MutableLiveData<Issue>()
    val searchedIssue: LiveData<Issue> get() = _searchedIssue

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

    fun getIssueListFromRepo(org: String, repo: String) {
        disposables.add(
            GitApiRepository.getIssueListFromRepo(org, repo)
                .subscribe({
                    _fetchedIssueList.value = it
                }, {
                    Util.Log.d(it.message)
                })
        )
    }

    fun searchIssueByIssueNumber(org: String, repo: String, issueNumber: Int) {
        disposables.add(
            GitApiRepository.searchIssueByIssueNumber(org, repo, issueNumber)
                .subscribe({
                    _searchedIssue.value = it
                }, {
                    Util.Log.d(it.message)
                })
        )
    }
}