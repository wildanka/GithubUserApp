package com.example.detail.data.remote

import androidx.paging.PagingData
import com.example.core.network.response.GithubUserDetailDto
import com.example.core.network.response.GithubUserDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface DetailUserRemoteDataSource {
    suspend fun getUserDetail(username: String): Response<GithubUserDetailDto>
    fun getFollowers(username: String): Flow<PagingData<GithubUserDto>>
    fun getFollowing(username: String): Flow<PagingData<GithubUserDto>>
}