package com.example.detail.di

import com.example.detail.data.DetailUserRepository
import com.example.detail.data.DetailUserRepositoryImpl
import com.example.detail.data.local.DetailUserLocalDataSource
import com.example.detail.data.local.DetailUserLocalDataSourceImpl
import com.example.detail.data.remote.DetailUserRemoteDataSource
import com.example.detail.data.remote.DetailUserRemoteDataSourceImpl
import com.example.detail.domain.DetailUserInteractor
import com.example.detail.domain.DetailUserUseCase
import com.example.detail.presentation.viewmodel.ProfileDetailViewModel
import com.example.core.database.GithubUserDatabase
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val userDetailModule = module {

    single { get<GithubUserDatabase>().githubUserDetailDao }

    single<DetailUserRemoteDataSource>{
        DetailUserRemoteDataSourceImpl(get())
    }
    single<DetailUserLocalDataSource>{
        DetailUserLocalDataSourceImpl(get())
    }

    single<DetailUserRepository>{
        DetailUserRepositoryImpl(get(), get())
    }

    single<DetailUserUseCase>{
        DetailUserInteractor(get())
    }

    viewModel {
        ProfileDetailViewModel(get())
    }
}
