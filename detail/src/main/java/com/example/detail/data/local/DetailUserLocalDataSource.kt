package com.example.detail.data.local

import com.example.core.database.entity.GithubUserDetailEntity
import kotlinx.coroutines.flow.Flow

interface DetailUserLocalDataSource {
    suspend fun getUser(userId: Int): Flow<GithubUserDetailEntity?>
    suspend fun saveUser(user: GithubUserDetailEntity)
}