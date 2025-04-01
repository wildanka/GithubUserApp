package com.example.detail.presentation.viewmodel

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest


//suspend fun <T : Any> Flow<PagingData<T>>.collectData(): List<T> {
//    val items = mutableListOf<T>()
//    this.collectLatest { pagingData ->
//        pagingData.collect { item ->
//            items.add(item)
//        }
//    }
//    return items
//}
