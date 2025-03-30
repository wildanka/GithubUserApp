package com.example.hcsgithubuser.detail.data.remote

import com.example.core.network.response.GithubUserDetailDto
import retrofit2.Response

interface DetailUserRemoteDataSource {
    suspend fun getUserDetail(username: String): Response<GithubUserDetailDto>
}