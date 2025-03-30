package com.example.detail.domain

import androidx.paging.PagingData
import com.example.arch.base.data.ApiResponse
import com.example.detail.data.DetailUserRepository
import com.example.core.database.entity.GithubUserDetailEntity
import com.example.core.network.response.GithubUserDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class DetailUserInteractor(private val detailUserRepository: DetailUserRepository) :
    DetailUserUseCase {
    override suspend fun getUserDetail(userId: Int, username: String): ApiResponse<GithubUserDetailEntity> {
        return withContext(Dispatchers.IO) { detailUserRepository.getUserDetail(userId, username) }
    }

    override fun getFollowers(username: String): Flow<PagingData<GithubUserDto>> {
        return detailUserRepository.getFollowers(username)
    }

    override fun getFollowing(username: String): Flow<PagingData<GithubUserDto>> {
        return detailUserRepository.getFollowing(username)

    }
    //
//    override suspend fun getFollowers(
//        username: String
//    ): ApiResponse<List<GithubUserDto>> {
//        return withContext(Dispatchers.IO) { detailUserRepository.getFollowers(username, page, perPage) }
//    }
}