package com.example.detail.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.arch.base.data.ApiResponse
import com.example.core.network.response.GithubUserDto
import com.example.detail.data.local.DetailUserLocalDataSource
import com.example.detail.data.mapper.GithubUserDetailDtoToEntMapper
import com.example.detail.data.remote.DetailUserRemoteDataSource
import com.example.detail.data.remote.FollowersPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

class DetailUserRepositoryImpl(private val remoteDataSource: DetailUserRemoteDataSource, private val localDataSource: DetailUserLocalDataSource) :
    DetailUserRepository {
    override suspend fun getUserDetail(userId: Int, username: String): ApiResponse<com.example.core.database.entity.GithubUserDetailEntity> {
        val userLocalData = localDataSource.getUser(userId).firstOrNull()

        return if(userLocalData == null){
            fetchUserDetailFromRemote(username)
        }else{
            ApiResponse.Success(
                data = userLocalData
            )
        }
    }

    private suspend fun fetchUserDetailFromRemote(username: String): ApiResponse<com.example.core.database.entity.GithubUserDetailEntity> {
        val remoteUserDetail = remoteDataSource.getUserDetail(username)

        if(remoteUserDetail.isSuccessful){
            val dtoData = remoteUserDetail.body()
            if(dtoData != null){
                val entity = GithubUserDetailDtoToEntMapper.map(dtoData)
                localDataSource.saveUser(entity)
                return ApiResponse.Success(
                    data = entity
                )
            }else{
                return ApiResponse.Error(remoteUserDetail.message())
            }
        }else{
            return ApiResponse.Error(remoteUserDetail.message())
        }
    }

    override fun getFollowers(username: String): Flow<PagingData<GithubUserDto>> {
        return remoteDataSource.getFollowers(username)
    }

    override fun getFollowing(username: String): Flow<PagingData<GithubUserDto>> {
        return remoteDataSource.getFollowing(username)
    }


}