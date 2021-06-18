package com.afrosin.popularlib.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "github_user_repos",
    foreignKeys = [ForeignKey(
        entity = GithubUser::class,
        parentColumns = ["id"],
        childColumns = ["user_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class GithubUserRepo(
    @PrimaryKey
    @SerializedName("id") val id: String,
    @ColumnInfo(name = "name")
    @SerializedName("name") val name: String,
    @ColumnInfo(name = "html_url")
    @SerializedName("html_url") val htmlUrl: String,
    @ColumnInfo(name = "forks_count")
    @SerializedName("forks_count") val forksCount: String,
    @ColumnInfo(name = "user_id")
    @SerializedName("user_id") val userId: String
)