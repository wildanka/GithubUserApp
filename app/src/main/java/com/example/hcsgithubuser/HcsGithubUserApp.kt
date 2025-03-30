package com.example.hcsgithubuser

import android.app.Application
import com.example.core.di.commonModule
import com.example.detail.di.userDetailModule
import com.example.hcsgithubuser.home.di.githubUserModule
import com.example.hcsgithubuser.search.di.searchModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class HcsGithubUserApp : Application() {
    companion object {
        @JvmField
        var instance: HcsGithubUserApp? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidLogger()
            androidContext(this@HcsGithubUserApp)
            modules(commonModule, githubUserModule, searchModule,
                com.example.detail.di.userDetailModule
            )
        }
    }
}