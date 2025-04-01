package com.example.hcsgithubuser.home.data.mapper

import com.example.core.database.entity.GithubUserEntity
import com.example.core.network.response.GithubUserDto
import org.junit.Assert.*
import org.junit.Test

class GithubUserDtoToEntMapperTest{
    @Test
    fun `map should correctly transform GithubUserDto to GithubUserEntity`() {
        // Arrange: Create a sample GithubUserDto
        val dto = GithubUserDto(
            id = 123,
            gistsUrl = "https://api.github.com/users/test/gists",
            reposUrl = "https://api.github.com/users/test/repos",
            userViewType = "user",
            followingUrl = "https://api.github.com/users/test/following",
            starredUrl = "https://api.github.com/users/test/starred",
            login = "testUser",
            followersUrl = "https://api.github.com/users/test/followers",
            type = "User",
            url = "https://api.github.com/users/test",
            subscriptionsUrl = "https://api.github.com/users/test/subscriptions",
            receivedEventsUrl = "https://api.github.com/users/test/received_events",
            avatarUrl = "https://avatars.githubusercontent.com/u/123?v=4",
            eventsUrl = "https://api.github.com/users/test/events",
            htmlUrl = "https://github.com/testUser",
            siteAdmin = false,
            gravatarId = "",
            nodeId = "MDQ6VXNlcjE=",
            organizationsUrl = "https://api.github.com/users/test/orgs",
            pageIndex = 1
        )

        // Act: Map the DTO to an entity
        val entity: GithubUserEntity = GithubUserDtoToEntMapper.map(dto)

        // Assert: Verify that fields are mapped correctly
        assertEquals(dto.id, entity.id)
        assertEquals(dto.gistsUrl, entity.gistsUrl)
        assertEquals(dto.reposUrl, entity.reposUrl)
        assertEquals(dto.userViewType, entity.userViewType)
        assertEquals(dto.followingUrl, entity.followingUrl)
        assertEquals(dto.starredUrl, entity.starredUrl)
        assertEquals(dto.login, entity.login)
        assertEquals(dto.followersUrl, entity.followersUrl)
        assertEquals(dto.type, entity.type)
        assertEquals(dto.url, entity.url)
        assertEquals(dto.subscriptionsUrl, entity.subscriptionsUrl)
        assertEquals(dto.receivedEventsUrl, entity.receivedEventsUrl)
        assertEquals(dto.avatarUrl, entity.avatarUrl)
        assertEquals(dto.eventsUrl, entity.eventsUrl)
        assertEquals(dto.htmlUrl, entity.htmlUrl)
        assertEquals(dto.siteAdmin, entity.siteAdmin)
        assertEquals(dto.gravatarId, entity.gravatarId)
        assertEquals(dto.nodeId, entity.nodeId)
        assertEquals(dto.organizationsUrl, entity.organizationsUrl)
        assertEquals(dto.pageIndex, entity.pageIndex)
    }
}