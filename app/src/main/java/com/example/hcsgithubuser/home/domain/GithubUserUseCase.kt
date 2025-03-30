package com.example.hcsgithubuser.home.domain

import com.example.core.network.response.GithubUserDto

interface GithubUserUseCase {
    suspend fun fetchUserList(page: Int, lastId: Int?) : com.example.arch.base.data.ApiResponse<List<GithubUserDto>>
}