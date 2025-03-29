package com.example.hcsgithubuser.search.data

import com.example.hcsgithubuser.base.data.ApiResponse
import com.example.hcsgithubuser.home.data.remote.response.GithubUserResponse

interface SearchUserRepository {
    suspend fun searchUser(page: Int, username: String): ApiResponse<GithubUserResponse>
}