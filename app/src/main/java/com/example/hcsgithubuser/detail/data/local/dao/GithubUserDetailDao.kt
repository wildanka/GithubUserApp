package com.example.hcsgithubuser.detail.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.hcsgithubuser.detail.data.local.entity.GithubUserDetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GithubUserDetailDao {
    @Upsert
    suspend fun upsertAll(githubUsers: List<GithubUserDetailEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(jobs: List<GithubUserDetailEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(jobs: GithubUserDetailEntity)

    @Query("SELECT * FROM githubUserDetail")
    fun pagingSource(): PagingSource<Int, GithubUserDetailEntity>

    @Query("DELETE FROM githubUserDetail")
    suspend fun clearTable()

    @Query("SELECT * FROM githubUserDetail WHERE id = :userId")
    fun getOne(userId: Int): Flow<GithubUserDetailEntity?>

}