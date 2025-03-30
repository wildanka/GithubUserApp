package com.example.hcsgithubuser.detail.data.local

import kotlinx.coroutines.flow.Flow

interface DetailUserLocalDataSource {
    suspend fun getUser(userId: Int): Flow<com.example.core.database.entity.GithubUserDetailEntity?>
    suspend fun saveUser(user: com.example.core.database.entity.GithubUserDetailEntity)
}