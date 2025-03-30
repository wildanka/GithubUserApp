package com.example.hcsgithubuser.detail.data.local

import com.example.core.database.dao.GithubUserDetailDao
import com.example.core.database.entity.GithubUserDetailEntity
import kotlinx.coroutines.flow.Flow


class DetailUserLocalDataSourceImpl(private val dao: GithubUserDetailDao) : DetailUserLocalDataSource {
    override suspend fun getUser(userId: Int): Flow<GithubUserDetailEntity?> {
        return dao.getOne(userId)
    }

    override suspend fun saveUser(user: GithubUserDetailEntity) {
        dao.insertOne(user)
    }

    suspend fun saveUsers(users: List<GithubUserDetailEntity>) {
        dao.insertAll(users)
    }
}