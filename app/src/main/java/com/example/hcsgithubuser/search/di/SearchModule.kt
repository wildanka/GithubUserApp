package com.example.hcsgithubuser.search.di

import com.example.hcsgithubuser.search.presentation.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    viewModel {
        SearchViewModel()
    }
}