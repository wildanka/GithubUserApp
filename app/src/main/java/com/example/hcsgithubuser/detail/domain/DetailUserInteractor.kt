package com.example.hcsgithubuser.detail.domain

import com.example.hcsgithubuser.detail.data.DetailUserRepository
import com.example.core.database.entity.GithubUserDetailEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailUserInteractor(private val detailUserRepository: DetailUserRepository) :
    DetailUserUseCase {
    override suspend fun getUserDetail(userId: Int, username: String): com.example.arch.base.data.ApiResponse<com.example.core.database.entity.GithubUserDetailEntity> {
        return withContext(Dispatchers.IO) { detailUserRepository.getUserDetail(userId, username) }
    }
}