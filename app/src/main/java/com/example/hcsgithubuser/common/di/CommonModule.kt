package com.example.hcsgithubuser.common.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.arch.base.data.AuthInterceptor
import com.example.hcsgithubuser.common.data.remote.GithubService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val commonModule = module {
    // Provide ChuckerInterceptor
    single {
        ChuckerInterceptor(androidApplication())
    }

    // Provide OkHttpClient with ChuckerInterceptor
    single {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(get<ChuckerInterceptor>()) // Inject ChuckerInterceptor from Koin
            .addInterceptor(com.example.arch.base.data.AuthInterceptor())
            .build()
    }

    // Provide Retrofit instance
    single {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(get()) // Inject OkHttpClient from Koin
            .build()
            .create(GithubService::class.java)
    }

}