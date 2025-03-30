package com.example.detail.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.network.GithubService
import com.example.core.network.response.GithubUserDetailDto
import com.example.core.network.response.GithubUserDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class DetailUserRemoteDataSourceImpl(private val api: GithubService) : DetailUserRemoteDataSource {
    override suspend fun getUserDetail(username: String): Response<GithubUserDetailDto> {
        return api.getUserDetail(username)
    }

    override fun getFollowers(username: String): Flow<PagingData<GithubUserDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { FollowersPagingSource(api, username) }
        ).flow
    }
}