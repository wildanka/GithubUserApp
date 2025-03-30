package com.example.common.data.remote

import com.example.common.data.remote.response.GithubUserDto
import com.example.common.data.remote.response.GithubUserResponse
import com.example.common.data.remote.response.GithubUserDetailDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("users")
    suspend fun getUsersAt(
        @Query("page") page: Int,
        @Query("since") lastId: Int?
    ): Response<List<GithubUserDto>>


    @GET("users/{username}")
    suspend fun getUserDetail(
        @Path("username") username: String,
    ): Response<GithubUserDetailDto>

    @GET("search/users")
    suspend fun searchUser(
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int,
        @Query("q") username: String,
    ): Response<GithubUserResponse>
}