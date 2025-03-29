package com.example.hcsgithubuser.search.domain

import com.example.arch.base.data.ApiResponse
import com.example.hcsgithubuser.home.data.remote.response.GithubUserResponse

interface SearchUserUseCase {
    suspend fun searchByUsername(username: String, page: Int) : com.example.arch.base.data.ApiResponse<GithubUserResponse>
}