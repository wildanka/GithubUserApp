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

    override fun initialisation(savedInstanceState: Bundle?) {
        //TODO : leave for initialisation scope
    }

    fun triggerSomething() {
        coroutineJob = viewModelScope.launch {
            when (val result = githubUserUseCase.fetchUserList(0)) {
                is ApiResponse.Success -> {
                    _userList.value = UiState.Success(data = result.data)
                }
                ApiResponse.Empty -> {
                    _userList.value = UiState.Loading
                }
                is ApiResponse.Error -> {
                    _userList.value = UiState.Error(errorMessage = result.errorMessage)
                    Log.d("WLDN", "VM api call error: ")
                }
            }
        }


    }
}