package com.example.hcsgithubuser.home.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "githubUser")
data class GithubUserEntity(

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
    var pageIndex: Int = -1
)
