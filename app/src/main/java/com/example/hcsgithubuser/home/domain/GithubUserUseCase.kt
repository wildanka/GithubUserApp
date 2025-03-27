package com.example.hcsgithubuser.home.domain

import com.example.hcsgithubuser.base.data.ApiResponse
import com.example.hcsgithubuser.home.data.remote.response.GithubUserDto

interface GithubUserUseCase {
    suspend fun fetchUserList(page: Int) : ApiResponse<List<GithubUserDto>>
}