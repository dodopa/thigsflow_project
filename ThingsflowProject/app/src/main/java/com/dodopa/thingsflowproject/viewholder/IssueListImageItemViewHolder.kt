package com.dodopa.thingsflowproject.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dodopa.thingsflowproject.R
import com.dodopa.thingsflowproject.databinding.ListItemImageIssueBinding

class IssueListImageItemViewHolder(
    parent: ViewGroup,
    view: View = LayoutInflater.from(parent.context)
        .inflate(R.layout.list_item_image_issue, parent, false)
) : SmartViewHolder(view) {

    private val binding = ListItemImageIssueBinding.bind(view)

    fun bindTo(url: String, onClicked: ((url: String) -> Unit)?) {
        Glide.with(binding.ivListItemIssue).load(url)
            .placeholder(R.drawable.placeholder_issue_list_image_item)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.ivListItemIssue)

        binding.ivListItemIssue.setOnClickListener {
            onClicked?.invoke(url)
        }
    }
}