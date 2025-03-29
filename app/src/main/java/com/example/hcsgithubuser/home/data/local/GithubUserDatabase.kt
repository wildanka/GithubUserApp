package com.example.hcsgithubuser.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hcsgithubuser.detail.data.local.dao.GithubUserDetailDao
import com.example.hcsgithubuser.detail.data.local.entity.GithubUserDetailEntity
import com.example.hcsgithubuser.home.data.local.dao.GithubUserDao
import com.example.hcsgithubuser.home.data.local.entity.GithubUserEntity

@Database(
    entities = [
        GithubUserEntity::class,
        GithubUserDetailEntity::class
    ],
    version = 3,

)
abstract class GithubUserDatabase : RoomDatabase() {
    abstract val githubUserDao: GithubUserDao
    abstract val githubUserDetailDao: GithubUserDetailDao
}