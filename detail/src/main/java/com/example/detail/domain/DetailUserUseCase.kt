package com.example.detail.domain

import androidx.paging.PagingData
import com.example.arch.base.data.ApiResponse
import com.example.core.database.entity.GithubUserDetailEntity
import com.example.core.network.response.GithubUserDto
import kotlinx.coroutines.flow.Flow

interface DetailUserUseCase {
    suspend fun getUserDetail(userId: Int, username: String): ApiResponse<GithubUserDetailEntity>
    fun getFollowers(username: String): Flow<PagingData<GithubUserDto>>
    fun getFollowing(username: String): Flow<PagingData<GithubUserDto>>
}