package com.example.hcsgithubuser.detail.di

import com.example.hcsgithubuser.detail.data.DetailUserRepository
import com.example.hcsgithubuser.detail.data.DetailUserRepositoryImpl
import com.example.hcsgithubuser.detail.data.local.DetailUserLocalDataSource
import com.example.hcsgithubuser.detail.data.local.DetailUserLocalDataSourceImpl
import com.example.hcsgithubuser.detail.data.remote.DetailUserRemoteDataSource
import com.example.hcsgithubuser.detail.data.remote.DetailUserRemoteDataSourceImpl
import com.example.hcsgithubuser.detail.domain.DetailUserInteractor
import com.example.hcsgithubuser.detail.domain.DetailUserUseCase
import com.example.hcsgithubuser.detail.presentation.viewmodel.ProfileDetailViewModel
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
