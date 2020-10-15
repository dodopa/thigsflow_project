package com.dodopa.thingsflowproject.model.entity

import com.google.gson.annotations.SerializedName

data class Issue(
    @SerializedName("number") val number: Int,
    @SerializedName("user") val user: User,
    @SerializedName("body") val body: String
)