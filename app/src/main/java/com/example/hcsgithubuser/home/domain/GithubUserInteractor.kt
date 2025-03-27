package com.example.hcsgithubuser.home.domain

import com.example.hcsgithubuser.base.data.ApiResponse
import com.example.hcsgithubuser.home.data.GithubUserRepository
import com.example.hcsgithubuser.home.data.remote.response.GithubUserDto

class GithubUserInteractor(private val githubUserRepository: GithubUserRepository) : GithubUserUseCase {
    override suspend fun fetchUserList(page: Int, lastId: Int?) : ApiResponse<List<GithubUserDto>> {
        return githubUserRepository.getUsers(page, lastId)
    }
}