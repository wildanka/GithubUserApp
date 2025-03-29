package com.example.hcsgithubuser.detail.domain

import com.example.hcsgithubuser.base.data.ApiResponse
import com.example.hcsgithubuser.detail.data.local.entity.GithubUserDetailEntity

interface DetailUserUseCase {
    suspend fun getUserDetail(userId: Int, username: String): ApiResponse<GithubUserDetailEntity>
}