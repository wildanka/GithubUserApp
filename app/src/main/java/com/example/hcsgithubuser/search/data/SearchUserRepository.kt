package com.example.hcsgithubuser.search.data

import com.example.core.network.response.GithubUserResponse

interface SearchUserRepository {
    suspend fun searchUser(page: Int, username: String): com.example.arch.base.data.ApiResponse<GithubUserResponse>
}