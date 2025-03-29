package com.example.hcsgithubuser.detail.data

import com.example.hcsgithubuser.base.data.ApiResponse
import com.example.hcsgithubuser.detail.data.local.DetailUserLocalDataSource
import com.example.hcsgithubuser.detail.data.local.entity.GithubUserDetailEntity
import com.example.hcsgithubuser.detail.data.mapper.GithubUserDetailDtoToEntMapper
import com.example.hcsgithubuser.detail.data.remote.DetailUserRemoteDataSource
import kotlinx.coroutines.flow.firstOrNull

class DetailUserRepositoryImpl(private val remoteDataSource: DetailUserRemoteDataSource, private val localDataSource: DetailUserLocalDataSource) : DetailUserRepository {
    override suspend fun getUserDetail(userId: Int, username: String): ApiResponse<GithubUserDetailEntity> {
        val userLocalData = localDataSource.getUser(userId).firstOrNull()

        return if(userLocalData == null){
            fetchUserDetailFromRemote(username)
        }else{
            ApiResponse.Success(
                data = userLocalData
            )
        }
    }

    private suspend fun fetchUserDetailFromRemote(username: String): ApiResponse<GithubUserDetailEntity>{
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
}