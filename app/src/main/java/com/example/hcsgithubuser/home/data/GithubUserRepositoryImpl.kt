package com.example.hcsgithubuser.home.data

import com.example.arch.base.data.ApiResponse
import com.example.core.database.entity.GithubUserEntity
import com.example.core.network.GithubService
import com.example.hcsgithubuser.home.data.mapper.GithubUserDtoToEntMapper
import com.example.core.network.response.GithubUserDto

class GithubUserRepositoryImpl(private val githubServiceApi: GithubService, private val githubUserDao: com.example.core.database.dao.GithubUserDao) : GithubUserRepository {

    override suspend fun fetchUsers(page: Int, lastId: Int?): ApiResponse<List<GithubUserDto>> {
        return try {
            val response = githubServiceApi.getUsersAt(page, lastId)

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    // Transform API data before inserting into DB
                    val newRemoteData = GithubUserDtoToEntMapper.mapList(body)

                    // Move DB operations to a separate function (for testability)
                    saveUsersToDb(newRemoteData, page)

                    ApiResponse.Success(body)
                } else {
                    ApiResponse.Empty
                }
            } else {
                val errorMsg = response.errorBody()?.string() ?: response.message()
                ApiResponse.Error(errorMsg)
            }
        } catch (e: Exception) {
            ApiResponse.Error(e.localizedMessage ?: "Unknown error")
        }
    }

    /**
     * Extracted function to save users to DB (makes it mockable in tests)
     */
    private suspend fun saveUsersToDb(users: List<GithubUserEntity>, page: Int) {
        if (page == 1) {
            githubUserDao.clearTable()
        }
        githubUserDao.insertAll(users)
    }


}