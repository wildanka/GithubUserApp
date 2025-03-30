package com.example.hcsgithubuser.home.data

import com.example.core.network.response.GithubUserDto

interface GithubUserRepository {
    suspend fun fetchUsers(page: Int, lastId: Int?): com.example.arch.base.data.ApiResponse<List<GithubUserDto>>
//    fun getUsers(): Flow<List<GithubUserEntity>>
//    suspend fun searchUser(page: Int, username: String): ApiResponse<List<GithubUserDto>>
}