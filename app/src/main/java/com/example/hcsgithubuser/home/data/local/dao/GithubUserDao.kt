package com.example.hcsgithubuser.home.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.hcsgithubuser.home.data.local.entity.GithubUserEntity

@Dao
interface GithubUserDao {

    @Upsert
    suspend fun upsertAll(githubUsers: List<GithubUserEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(jobs: List<GithubUserEntity>)

    @Query("SELECT * FROM githubUser")
    fun pagingSource(): PagingSource<Int, GithubUserEntity>

    @Query("DELETE FROM githubUser")
    suspend fun clearTable()
//    @Query("SELECT * FROM githubUser WHERE pageIndex >= 0 ORDER BY pageIndex")
//    fun getAllWithIndex(): Flow<List<GithubUserEntity>>
}