package com.dodopa.thingsflowproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel : GitApiViewModel() {

    private var _searchRepoName = MutableLiveData<String>()
    val searchRepoName: LiveData<String> get() = _searchRepoName

    fun setSearchRepoName(name: String) {
        _searchRepoName.value = name
    }
}