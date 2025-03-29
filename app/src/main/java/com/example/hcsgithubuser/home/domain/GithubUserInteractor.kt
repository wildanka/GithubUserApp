package com.example.hcsgithubuser.home.domain

import com.example.hcsgithubuser.base.data.ApiResponse
import com.example.hcsgithubuser.home.data.GithubUserRepository
import com.example.hcsgithubuser.home.data.remote.response.GithubUserDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GithubUserInteractor(private val githubUserRepository: GithubUserRepository) :
    GithubUserUseCase {
    override suspend fun fetchUserList(page: Int, lastId: Int?): ApiResponse<List<GithubUserDto>> {
        return withContext(Dispatchers.IO) {
            githubUserRepository.fetchUsers(page, lastId)
        }
    }
}