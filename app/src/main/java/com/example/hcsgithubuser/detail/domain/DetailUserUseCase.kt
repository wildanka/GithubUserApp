package com.example.hcsgithubuser.detail.domain

import com.example.arch.base.data.ApiResponse
import com.example.hcsgithubuser.detail.data.local.entity.GithubUserDetailEntity

interface DetailUserUseCase {
    suspend fun getUserDetail(userId: Int, username: String): com.example.arch.base.data.ApiResponse<GithubUserDetailEntity>
}