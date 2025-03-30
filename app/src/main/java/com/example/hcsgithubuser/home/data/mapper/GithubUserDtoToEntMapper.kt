package com.example.hcsgithubuser.home.data.mapper

import com.example.arch.base.data.mapper.ListMapper
import com.example.core.network.response.GithubUserDto

object GithubUserDtoToEntMapper :
    ListMapper<GithubUserDto, com.example.core.database.entity.GithubUserEntity> {
    override fun map(source: GithubUserDto): com.example.core.database.entity.GithubUserEntity {
         return com.example.core.database.entity.GithubUserEntity(
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