package com.example.core.network

import com.example.core.network.response.GithubUserDetailDto
import com.example.core.network.response.GithubUserDto
import com.example.core.network.response.GithubUserResponse
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

    @GET("users/{username}/followers")
    suspend fun getFollowers(
        @Path("username") username: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 20
    ): List<GithubUserDto>
}