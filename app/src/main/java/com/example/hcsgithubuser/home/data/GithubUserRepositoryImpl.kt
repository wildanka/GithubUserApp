package com.example.hcsgithubuser.home.data

import com.example.hcsgithubuser.base.data.ApiResponse
import com.example.hcsgithubuser.home.data.local.dao.GithubUserDao
import com.example.hcsgithubuser.home.data.mapper.GithubUserDtoToEntMapper
import com.example.hcsgithubuser.home.data.remote.GithubService
import com.example.hcsgithubuser.home.data.remote.response.GithubUserDto

class GithubUserRepositoryImpl(private val githubServiceApi: GithubService, private val githubUserDao: GithubUserDao) : GithubUserRepository {

    override suspend fun fetchUsers(page: Int, lastId: Int?): ApiResponse<List<GithubUserDto>> {
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

                ApiResponse.Success(
                    data = body
                )
            } else {
                ApiResponse.Empty
            }
        } else {
            return ApiResponse.Error(response.message())
        }
    }

    override suspend fun searchUser(
        page: Int,
        username: String
    ): ApiResponse<List<GithubUserDto>> {
        val response = githubServiceApi.searchUser(page, 40, username)


        if (response.isSuccessful) {
            val body = response.body()
            return if (body != null) {
                ApiResponse.Success(
                    data = body.items.orEmpty()
                )
            } else {
                ApiResponse.Empty
            }

        } else {
            return ApiResponse.Error(response.message())
        }
    }

}