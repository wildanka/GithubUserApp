package com.example.hcsgithubuser.search.data

import com.example.arch.base.data.ApiResponse
import com.example.hcsgithubuser.common.data.remote.GithubService
import com.example.hcsgithubuser.common.util.Const
import com.example.hcsgithubuser.home.data.remote.response.GithubUserResponse

class SearchUserRepositoryImpl(private val api: GithubService) : SearchUserRepository {
    override suspend fun searchUser(
        page: Int,
        username: String
    ): com.example.arch.base.data.ApiResponse<GithubUserResponse> {
        val response = api.searchUser(page, Const.paginationSize, username)

        if (response.isSuccessful) {
            val body = response.body()
            return if (body != null) {
                com.example.arch.base.data.ApiResponse.Success(
                    data = body
                )
            } else {
                com.example.arch.base.data.ApiResponse.Empty
            }

        } else {
            return com.example.arch.base.data.ApiResponse.Error(response.message())
        }
    }
}