package com.example.hcsgithubuser.home.data.remote

import com.example.hcsgithubuser.home.data.remote.response.GithubUserDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {

    @GET("users")
    suspend fun getUsersAt(
        @Query("page") page: Int,
        @Query("since") lastId: Int?
    ): Response<List<GithubUserDto>>
}