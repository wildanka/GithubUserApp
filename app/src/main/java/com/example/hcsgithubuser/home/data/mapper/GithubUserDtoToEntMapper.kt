package com.example.hcsgithubuser.home.data.mapper

import com.example.arch.base.data.mapper.ListMapper
import com.example.hcsgithubuser.home.data.local.entity.GithubUserEntity
import com.example.hcsgithubuser.home.data.remote.response.GithubUserDto

object GithubUserDtoToEntMapper :
    com.example.arch.base.data.mapper.ListMapper<GithubUserDto, GithubUserEntity> {
    override fun map(source: GithubUserDto): GithubUserEntity {
         return GithubUserEntity(
            id = source.id,
            gistsUrl = source.gistsUrl,
            reposUrl = source.reposUrl,
            userViewType = source.userViewType,
            followingUrl = source.followingUrl,
            starredUrl = source.starredUrl,
            login = source.login,
            followersUrl = source.followersUrl,
            type = source.type,
            url = source.url,
            subscriptionsUrl = source.subscriptionsUrl,
            receivedEventsUrl = source.receivedEventsUrl,
            avatarUrl = source.avatarUrl,
            eventsUrl = source.eventsUrl,
            htmlUrl = source.htmlUrl,
            siteAdmin = source.siteAdmin,
            gravatarId = source.gravatarId,
            nodeId = source.nodeId,
            organizationsUrl = source.organizationsUrl,
            pageIndex = source.pageIndex
        )
    }
}