package com.example.hcsgithubuser.home.presentation

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.hcsgithubuser.base.data.ApiResponse
import com.example.hcsgithubuser.base.data.UiState
import com.example.hcsgithubuser.base.presentation.BaseViewModel
import com.example.hcsgithubuser.home.data.remote.response.GithubUserDto
import com.example.hcsgithubuser.home.domain.GithubUserUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val githubUserUseCase: GithubUserUseCase) : BaseViewModel() {
    private val _userList: MutableStateFlow<UiState<List<GithubUserDto>>> =
        MutableStateFlow(UiState.Loading)
    val userList: StateFlow<UiState<List<GithubUserDto>>> get() = _userList

    private var coroutineJob: Job? = null
    var page = 0
    var lastId : Int? = null
    var isLoading = false

    override fun initialisation(savedInstanceState: Bundle?) {
        //TODO : leave for initialisation scope
    }

    fun refreshList(){
        page = 0
        lastId = null
        coroutineJob?.cancel()
        _userList.value = UiState.Loading
        triggerSomething()
    }


    fun triggerSomething() {
        coroutineJob = viewModelScope.launch {
            if(isLoading){
                return@launch
            }
            isLoading = true
            page++
            when (val result = githubUserUseCase.fetchUserList(page, lastId)) {
                is ApiResponse.Success -> {
                    val currentList = (userList.value as? UiState.Success)?.data ?: emptyList()
                    val newList = currentList + result.data // Append new data

                    _userList.value = UiState.Success(data = newList)

                    lastId = result.data.lastOrNull()?.id
                    isLoading = false
                }
                is ApiResponse.Empty -> {
                    _userList.value = UiState.Loading
                    isLoading = false
                }
                is ApiResponse.Error -> {
                    _userList.value = UiState.Error(errorMessage = result.errorMessage)
                    isLoading = false
                }
            }
        }
    }
}