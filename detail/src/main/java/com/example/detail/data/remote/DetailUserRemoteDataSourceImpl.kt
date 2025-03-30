package com.example.detail.data.remote

import com.example.core.network.GithubService
import com.example.core.network.response.GithubUserDetailDto
import retrofit2.Response

class DetailUserRemoteDataSourceImpl(private val api: GithubService) : DetailUserRemoteDataSource {
    override suspend fun getUserDetail(username: String): Response<GithubUserDetailDto> {
        return api.getUserDetail(username)
    }
}