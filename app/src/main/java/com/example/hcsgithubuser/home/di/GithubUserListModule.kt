package com.example.hcsgithubuser.home.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.hcsgithubuser.base.data.AuthInterceptor
import com.example.hcsgithubuser.home.data.GithubUserRepository
import com.example.hcsgithubuser.home.data.GithubUserRepositoryImpl
import com.example.hcsgithubuser.home.data.remote.GithubService
import com.example.hcsgithubuser.home.domain.GithubUserInteractor
import com.example.hcsgithubuser.home.domain.GithubUserUseCase
import com.example.hcsgithubuser.home.presentation.HomeViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


val githubUserModule = module {
    // Provide ChuckerInterceptor
    single {
        ChuckerInterceptor(androidApplication())
    }

    // Provide OkHttpClient with ChuckerInterceptor
    single {
        val context: Context = androidContext()
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(get<ChuckerInterceptor>()) // Inject ChuckerInterceptor from Koin
            .addInterceptor(AuthInterceptor())
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

    single<GithubUserRepository>{
        GithubUserRepositoryImpl(get())
    }

    single<GithubUserUseCase>{
        GithubUserInteractor(get())
    }

    viewModel {
        HomeViewModel(get())
    }
}