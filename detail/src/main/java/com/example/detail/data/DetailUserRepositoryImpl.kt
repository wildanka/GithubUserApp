package com.example.detail.data

import com.example.detail.data.local.DetailUserLocalDataSource
import com.example.core.database.entity.GithubUserDetailEntity
import com.example.detail.data.mapper.GithubUserDetailDtoToEntMapper
import com.example.detail.data.remote.DetailUserRemoteDataSource
import kotlinx.coroutines.flow.firstOrNull

class DetailUserRepositoryImpl(private val remoteDataSource: DetailUserRemoteDataSource, private val localDataSource: DetailUserLocalDataSource) :
    DetailUserRepository {
    override suspend fun getUserDetail(userId: Int, username: String): com.example.arch.base.data.ApiResponse<com.example.core.database.entity.GithubUserDetailEntity> {
        val userLocalData = localDataSource.getUser(userId).firstOrNull()

        return if(userLocalData == null){
            fetchUserDetailFromRemote(username)
        }else{
            com.example.arch.base.data.ApiResponse.Success(
                data = userLocalData
            )
        }
    }

    private suspend fun fetchUserDetailFromRemote(username: String): com.example.arch.base.data.ApiResponse<com.example.core.database.entity.GithubUserDetailEntity> {
        val remoteUserDetail = remoteDataSource.getUserDetail(username)

        if(remoteUserDetail.isSuccessful){
            val dtoData = remoteUserDetail.body()
            if(dtoData != null){
                val entity = GithubUserDetailDtoToEntMapper.map(dtoData)
                localDataSource.saveUser(entity)
                return com.example.arch.base.data.ApiResponse.Success(
                    data = entity
                )
            }else{
                return com.example.arch.base.data.ApiResponse.Error(remoteUserDetail.message())
            }
        }else{
            return com.example.arch.base.data.ApiResponse.Error(remoteUserDetail.message())
        }
    }
}