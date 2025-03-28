package com.example.hcsgithubuser.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hcsgithubuser.home.data.local.dao.GithubUserDao
import com.example.hcsgithubuser.home.data.local.entity.GithubUserEntity

@Database(
    entities = [GithubUserEntity::class],
    version = 1
)
abstract class GithubUserDatabase : RoomDatabase() {
    abstract val githubUserDao: GithubUserDao
}