package com.example.hcsgithubuser.home.data

import com.example.hcsgithubuser.base.data.ApiResponse
import com.example.hcsgithubuser.home.data.remote.response.GithubUserDto

interface GithubUserRepository {
    suspend fun getUsers(page: Int, lastId: Int?): ApiResponse<List<GithubUserDto>>
}