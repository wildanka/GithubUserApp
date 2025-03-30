package com.example.hcsgithubuser.home.domain

import com.example.arch.base.data.ApiResponse
import com.example.common.data.remote.response.GithubUserDto

interface GithubUserUseCase {
    suspend fun fetchUserList(page: Int, lastId: Int?) : com.example.arch.base.data.ApiResponse<List<GithubUserDto>>
}