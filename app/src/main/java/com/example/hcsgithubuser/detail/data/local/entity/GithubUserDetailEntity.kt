package com.example.hcsgithubuser.detail.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "githubUserDetail")
data class GithubUserDetailEntity(

    @PrimaryKey
    val id: Int? = null,
    val gistsUrl: String? = null,
    val reposUrl: String? = null,
    val userViewType: String? = null,
    val followingUrl: String? = null,
    val starredUrl: String? = null,
    val login: String? = null,
    val followersUrl: String? = null,
    val type: String? = null,
    val url: String? = null,
    val subscriptionsUrl: String? = null,
    val receivedEventsUrl: String? = null,
    val avatarUrl: String? = null,
    val eventsUrl: String? = null,
    val htmlUrl: String? = null,
    val siteAdmin: Boolean? = null,
    val gravatarId: String? = null,
    val nodeId: String? = null,
    val organizationsUrl: String? = null,
    val bio: String? = null,
    val createdAt: String? = null,
    val blog: String? = null,
    val updatedAt: String? = null,
    val company: String? = null,
    val publicRepos: Int? = null,
    val email: String? = null,
    val hireable: Boolean? = null,
    val publicGists: Int? = null,
    val followers: Int? = null,
    val following: Int? = null,
    val name: String? = null,
    val location: String? = null,
    val twitterUsername: String? = null,
    var pageIndex: Int = -1
)
