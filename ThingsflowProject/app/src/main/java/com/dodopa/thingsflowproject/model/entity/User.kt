package com.dodopa.thingsflowproject.model.entity

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login") val nickname: String,
    @SerializedName("avatar_url") val avatarUrl: String
)