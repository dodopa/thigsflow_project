package com.dodopa.thingsflowproject.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dodopa.thingsflowproject.R
import com.dodopa.thingsflowproject.databinding.ListItemImageIssueBinding

class EmptyViewHolder(
    parent: ViewGroup,
    view: View = LayoutInflater.from(parent.context)
        .inflate(R.layout.list_item_empty, parent, false)
) : SmartViewHolder(view)