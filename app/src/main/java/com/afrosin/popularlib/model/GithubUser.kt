package com.afrosin.popularlib.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "github_users")
data class GithubUser(
    @PrimaryKey
    @SerializedName("id") val id: String,
    @ColumnInfo(name = "login")
    @SerializedName("login") val login: String,
    @ColumnInfo(name = "name")
    @SerializedName("name") val name: String?,
    @ColumnInfo(name = "avatar_url")
    @SerializedName("avatar_url") val avatarUrl: String
)