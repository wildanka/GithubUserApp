package com.example.hcsgithubuser.search.data

import com.example.hcsgithubuser.base.data.ApiResponse
import com.example.hcsgithubuser.common.data.remote.GithubService
import com.example.hcsgithubuser.common.util.Const
import com.example.hcsgithubuser.home.data.remote.response.GithubUserResponse

class SearchUserRepositoryImpl(private val api: GithubService) : SearchUserRepository {
    override suspend fun searchUser(
        page: Int,
        username: String
    ): ApiResponse<GithubUserResponse> {
        val response = api.searchUser(page, Const.paginationSize, username)

        if (response.isSuccessful) {
            val body = response.body()
            return if (body != null) {
                ApiResponse.Success(
                    data = body
                )
            } else {
                ApiResponse.Empty
            }

        } else {
            return ApiResponse.Error(response.message())
        }
    }
}