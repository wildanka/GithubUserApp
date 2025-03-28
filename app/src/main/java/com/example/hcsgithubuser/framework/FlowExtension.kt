package com.example.hcsgithubuser.framework

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun <T> Flow<T>.collectIn(scope: CoroutineScope, action: suspend (value: T) -> Unit) {
    scope.launch {
        collect {
            action(it)
        }
    }
}
