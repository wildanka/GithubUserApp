package com.example.hcsgithubuser.detail.data

import com.example.hcsgithubuser.base.data.ApiResponse
import com.example.hcsgithubuser.detail.data.local.entity.GithubUserDetailEntity

interface DetailUserRepository {
    suspend fun getUserDetail(userId: Int, username: String): ApiResponse<GithubUserDetailEntity>
}