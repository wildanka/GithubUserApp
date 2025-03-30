package com.example.detail.domain

import com.example.core.database.entity.GithubUserDetailEntity

interface DetailUserUseCase {
    suspend fun getUserDetail(userId: Int, username: String): com.example.arch.base.data.ApiResponse<com.example.core.database.entity.GithubUserDetailEntity>
}