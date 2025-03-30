package com.example.hcsgithubuser.detail.data.remote

import com.example.common.data.remote.GithubService
import com.example.common.data.remote.response.GithubUserDetailDto
import retrofit2.Response

class DetailUserRemoteDataSourceImpl(private val api: GithubService) : DetailUserRemoteDataSource{
    override suspend fun getUserDetail(username: String): Response<GithubUserDetailDto> {
        return api.getUserDetail(username)
    }
}