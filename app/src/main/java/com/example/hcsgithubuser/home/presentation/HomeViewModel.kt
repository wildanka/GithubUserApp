package com.example.hcsgithubuser.home.presentation

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.arch.base.presentation.BaseViewModel
import com.example.arch.framework.Event
import com.example.arch.framework.AppUtility
import com.example.core.database.GithubUserDatabase
import com.example.hcsgithubuser.home.domain.GithubUserUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val githubUserUseCase: GithubUserUseCase,
    private val database: GithubUserDatabase,
    private val context: Application
) : BaseViewModel() {

    private var coroutineJob: Job? = null
    var page = 0
    var lastId: Int? = null

    private val _isLoading = MutableStateFlow(false)  // Internal mutable state
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _errorMessage = MutableLiveData<Event<String>>()
    val errorMessage: LiveData<Event<String>> = _errorMessage

    val items = Pager(
        PagingConfig(50)
    ) {
        database.githubUserDao.pagingSource()
    }.flow


    override fun initialisation(savedInstanceState: Bundle?) {
        loadUserDataRemotely()
    }

    fun refreshList() {
        _isLoading.value = true
        if (AppUtility.isNetworkAvailable(context.applicationContext)) {
            page = 0
            lastId = null
            coroutineJob?.cancel()
            _isLoading.value = false
            loadUserDataRemotely()
        } else {
            _isLoading.value = false
            _errorMessage.postValue(Event("No network connection"))
        }
    }


    fun loadUserDataRemotely() {
        if (AppUtility.isNetworkAvailable(context.applicationContext)) {
            fetchGithubUser()
        } else {
            _isLoading.value = false
            _errorMessage.postValue(Event("No network connection"))
        }
    }

    private fun fetchGithubUser() {
        coroutineJob = viewModelScope.launch {
            if (isLoading.value) {
                return@launch
            }
            _isLoading.value = true
            page++
            when (val result = githubUserUseCase.fetchUserList(page, lastId)) {
                is com.example.arch.base.data.ApiResponse.Success -> {
                    lastId = result.data.lastOrNull()?.id
                    _isLoading.value = false
                }

                is com.example.arch.base.data.ApiResponse.Empty -> {
                    _isLoading.value = false
                }

                is com.example.arch.base.data.ApiResponse.Error -> {
                    page--
                    _isLoading.value = false
                }
            }
        }
    }
}