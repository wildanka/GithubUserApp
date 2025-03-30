package com.example.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.database.dao.GithubUserDao
import com.example.core.database.dao.GithubUserDetailDao
import com.example.core.database.entity.GithubUserDetailEntity
import com.example.core.database.entity.GithubUserEntity

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