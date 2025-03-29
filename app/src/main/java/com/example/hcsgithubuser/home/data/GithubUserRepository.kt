package com.example.hcsgithubuser.home.data

import com.example.hcsgithubuser.base.data.ApiResponse
import com.example.hcsgithubuser.home.data.remote.response.GithubUserDto

interface GithubUserRepository {
    suspend fun fetchUsers(page: Int, lastId: Int?): ApiResponse<List<GithubUserDto>>
//    fun getUsers(): Flow<List<GithubUserEntity>>
//    suspend fun searchUser(page: Int, username: String): ApiResponse<List<GithubUserDto>>
}