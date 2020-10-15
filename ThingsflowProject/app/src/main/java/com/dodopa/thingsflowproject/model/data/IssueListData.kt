package com.dodopa.thingsflowproject.model.data

import com.dodopa.thingsflowproject.adapter.SearchIssuesAdapter
import com.dodopa.thingsflowproject.model.entity.Issue

data class IssueListData(
    val issue: Issue? = null,
    val viewType: SearchIssuesAdapter.ViewType,
    val imageUrl: String = ""
)