package com.example.hcsgithubuser.detail.data.local

import com.example.hcsgithubuser.detail.data.local.entity.GithubUserDetailEntity
import kotlinx.coroutines.flow.Flow

interface DetailUserLocalDataSource {
    suspend fun getUser(userId: Int): Flow<GithubUserDetailEntity?>
    suspend fun saveUser(user: GithubUserDetailEntity)
}