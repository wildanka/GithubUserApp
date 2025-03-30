package com.example.hcsgithubuser.search.domain

import com.example.core.network.response.GithubUserResponse

interface SearchUserUseCase {
    suspend fun searchByUsername(username: String, page: Int) : com.example.arch.base.data.ApiResponse<GithubUserResponse>
}