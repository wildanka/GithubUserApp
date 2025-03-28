package com.example.hcsgithubuser.home.data.local

import com.example.hcsgithubuser.home.data.local.entity.GithubUserEntity
import com.example.hcsgithubuser.home.data.remote.response.GithubUserDto

fun GithubUserDto.toEntity(): GithubUserEntity {
    return GithubUserEntity(
        id = id,
        gistsUrl = gistsUrl,
        reposUrl = reposUrl,
        userViewType = userViewType,
        followingUrl = followingUrl,
        starredUrl = starredUrl,
        login = login,
        followersUrl = followersUrl,
        type = type,
        url = url,
        subscriptionsUrl = subscriptionsUrl,
        receivedEventsUrl = receivedEventsUrl,
        avatarUrl = avatarUrl,
        eventsUrl = eventsUrl,
        htmlUrl = htmlUrl,
        siteAdmin = siteAdmin,
        gravatarId = gravatarId,
        nodeId = nodeId,
        organizationsUrl = organizationsUrl,
        pageIndex = pageIndex
    )
}


fun GithubUserEntity.toDto(): GithubUserDto {
    return GithubUserDto(
        id = id,
        gistsUrl = gistsUrl,
        reposUrl = reposUrl,
        userViewType = userViewType,
        followingUrl = followingUrl,
        starredUrl = starredUrl,
        login = login,
        followersUrl = followersUrl,
        type = type,
        url = url,
        subscriptionsUrl = subscriptionsUrl,
        receivedEventsUrl = receivedEventsUrl,
        avatarUrl = avatarUrl,
        eventsUrl = eventsUrl,
        htmlUrl = htmlUrl,
        siteAdmin = siteAdmin,
        gravatarId = gravatarId,
        nodeId = nodeId,
        organizationsUrl = organizationsUrl,
        pageIndex = pageIndex
    )
}

