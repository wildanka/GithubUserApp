package com.example.hcsgithubuser.base.data

import com.example.hcsgithubuser.BuildConfig.GITHUB_API_VERSION
import com.example.hcsgithubuser.BuildConfig.GITHUB_TOKEN
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("Accept", "application/vnd.github+json")
                .addHeader("Authorization", "Bearer $GITHUB_TOKEN")
                .addHeader("X-GitHub-Api-Version", GITHUB_API_VERSION)
                .build()
        )
    }
}