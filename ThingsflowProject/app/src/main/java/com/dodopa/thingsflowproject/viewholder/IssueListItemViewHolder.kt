package com.dodopa.thingsflowproject.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dodopa.thingsflowproject.R
import com.dodopa.thingsflowproject.databinding.ListItemIssueBinding
import com.dodopa.thingsflowproject.model.entity.Issue

class IssueListItemViewHolder(
    parent: ViewGroup,
    view: View = LayoutInflater.from(parent.context)
        .inflate(R.layout.list_item_issue, parent, false)
) : SmartViewHolder(view) {

    private val binding = ListItemIssueBinding.bind(view)

    fun bindTo(issue: Issue, onClicked: ((issue: Issue) -> Unit)?) {
        binding.tvListItemIssue.text = "#${issue.number} ${issue.title}"
        binding.clRootListItemIssue.setOnClickListener {
            onClicked?.invoke(issue)
        }
    }
}