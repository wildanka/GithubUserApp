package com.example.hcsgithubuser.search.domain

import com.example.hcsgithubuser.base.data.ApiResponse
import com.example.hcsgithubuser.home.data.remote.response.GithubUserResponse
import com.example.hcsgithubuser.search.data.SearchUserRepository

class SearchUserInteractor(private val searchUserRepository: SearchUserRepository) :
    SearchUserUseCase {

    override suspend fun searchByUsername(
        username: String,
        page: Int
    ): ApiResponse<GithubUserResponse> {
        return searchUserRepository.searchUser(page, username)
    }
}