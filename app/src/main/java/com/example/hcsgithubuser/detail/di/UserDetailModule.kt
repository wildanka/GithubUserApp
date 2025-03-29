package com.example.hcsgithubuser.detail.di

import com.example.hcsgithubuser.detail.presentation.viewmodel.ProfileDetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val userDetailModule = module {

    viewModel {
        ProfileDetailViewModel()
    }
}
