package com.example.hcsgithubuser.search.domain

import com.example.core.network.response.GithubUserResponse
import com.example.hcsgithubuser.search.data.SearchUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchUserInteractor(private val searchUserRepository: SearchUserRepository) :
    SearchUserUseCase {

    override suspend fun searchByUsername(
        username: String,
        page: Int
    ): com.example.arch.base.data.ApiResponse<GithubUserResponse> {
        return withContext(Dispatchers.IO){
            searchUserRepository.searchUser(page, username)
        }
    }
}