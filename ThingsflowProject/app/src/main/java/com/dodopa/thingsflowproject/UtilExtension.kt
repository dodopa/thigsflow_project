package com.dodopa.thingsflowproject

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun ImageView.setAvatar(url: String) = Glide.with(this).load(url)
    .placeholder(R.drawable.placeholder_avatar)
    .transition(DrawableTransitionOptions.withCrossFade())
    .into(this)