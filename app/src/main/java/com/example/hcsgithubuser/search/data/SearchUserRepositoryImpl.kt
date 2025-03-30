package com.example.hcsgithubuser.search.data

import com.example.core.network.GithubService
import com.example.core.common.presentation.util.Const
import com.example.core.network.response.GithubUserResponse

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