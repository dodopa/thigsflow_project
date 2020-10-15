package com.dodopa.thingsflowproject.model.entity

import com.google.gson.annotations.SerializedName

data class Issue(
    @SerializedName("number") val number: Int,
    @SerializedName("user") val user: User,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String,
    @SerializedName("repository_url") val repositoryUrl: String
) {
    val repositoryName: String get() = repositoryUrl.split("/").last()
}