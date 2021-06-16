package com.afrosin.popularlib.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUserRepo(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("html_url") val htmlUrl: String
) : Parcelable
