package com.example.hcsgithubuser.search.di

import com.example.hcsgithubuser.search.data.SearchUserRepository
import com.example.hcsgithubuser.search.data.SearchUserRepositoryImpl
import com.example.hcsgithubuser.search.domain.SearchUserInteractor
import com.example.hcsgithubuser.search.domain.SearchUserUseCase
import com.example.hcsgithubuser.search.presentation.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    single<SearchUserRepository>{
        SearchUserRepositoryImpl(get())
    }

    single<SearchUserUseCase>{
        SearchUserInteractor(get())
    }

    viewModel {
        SearchViewModel(get())
    }
}