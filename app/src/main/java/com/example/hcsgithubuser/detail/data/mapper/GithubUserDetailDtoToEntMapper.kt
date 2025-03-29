package com.example.hcsgithubuser.detail.data.mapper

import com.example.hcsgithubuser.base.data.mapper.ListMapper
import com.example.hcsgithubuser.detail.data.remote.response.GithubUserDetailDto
import com.example.hcsgithubuser.detail.data.local.entity.GithubUserDetailEntity

object GithubUserDetailDtoToEntMapper : ListMapper<GithubUserDetailDto, GithubUserDetailEntity> {
    override fun map(source: GithubUserDetailDto): GithubUserDetailEntity {
        return GithubUserDetailEntity(
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
            bio = source.bio,
            createdAt = source.createdAt,
            blog = source.blog,
            updatedAt = source.updatedAt,
            company = source.company,
            publicRepos = source.publicRepos,
            email = source.email,
            hireable = source.hireable,
            publicGists = source.publicGists,
            followers = source.followers,
            following = source.following,
            name = source.name,
            location = source.location,
            twitterUsername = source.twitterUsername,
            pageIndex = source.pageIndex
        )
    }
}