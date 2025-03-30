package com.example.hcsgithubuser.home.data

import com.example.arch.base.data.ApiResponse
import com.example.common.data.remote.GithubService
import com.example.hcsgithubuser.home.data.local.dao.GithubUserDao
import com.example.hcsgithubuser.home.data.mapper.GithubUserDtoToEntMapper
import com.example.common.data.remote.response.GithubUserDto

class GithubUserRepositoryImpl(private val githubServiceApi: GithubService, private val githubUserDao: GithubUserDao) : GithubUserRepository {

    override suspend fun fetchUsers(page: Int, lastId: Int?): com.example.arch.base.data.ApiResponse<List<GithubUserDto>> {
        val response = githubServiceApi.getUsersAt(page, lastId)

        if (response.isSuccessful) {
            val body = response.body()
            return if (body != null) {
                //add data to the database
                val newRemoteData = GithubUserDtoToEntMapper.mapList(body)
                if(page == 1){
                    githubUserDao.clearTable()
                }
                githubUserDao.insertAll(newRemoteData)

                com.example.arch.base.data.ApiResponse.Success(
                    data = body
                )
            } else {
                com.example.arch.base.data.ApiResponse.Empty
            }
        } else {
            return com.example.arch.base.data.ApiResponse.Error(response.message())
        }
    }



}