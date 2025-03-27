package com.example.hcsgithubuser.home.data

import com.example.hcsgithubuser.base.data.ApiResponse
import com.example.hcsgithubuser.home.data.remote.GithubService
import com.example.hcsgithubuser.home.data.remote.response.GithubUserDto

class GithubUserRepositoryImpl(private val githubServiceApi: GithubService) : GithubUserRepository {
    override suspend fun getUsers(page: Int, lastId: Int?): ApiResponse<List<GithubUserDto>> {
        val response = githubServiceApi.getUsersAt(page, lastId)


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