package com.example.detail.data

import com.example.core.database.entity.GithubUserDetailEntity

interface DetailUserRepository {
    suspend fun getUserDetail(userId: Int, username: String): com.example.arch.base.data.ApiResponse<com.example.core.database.entity.GithubUserDetailEntity>
}