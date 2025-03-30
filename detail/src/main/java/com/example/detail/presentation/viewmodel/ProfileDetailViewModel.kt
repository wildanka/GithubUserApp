package com.example.detail.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.database.entity.GithubUserDetailEntity
import com.example.detail.domain.DetailUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileDetailViewModel(private val detailUserUseCase: DetailUserUseCase) : ViewModel() {
    private val _userDetail: MutableStateFlow<com.example.arch.base.data.UiState<GithubUserDetailEntity>> =
        MutableStateFlow(com.example.arch.base.data.UiState.Loading)
    val userDetail: StateFlow<com.example.arch.base.data.UiState<GithubUserDetailEntity>> get() = _userDetail


    fun checkDetailFromDb(userId: Int, username: String) {
        viewModelScope.launch {
            when(val result = detailUserUseCase.getUserDetail(userId, username)){
                is com.example.arch.base.data.ApiResponse.Empty -> {
                    _userDetail.value = com.example.arch.base.data.UiState.Error("No data found")
                }
                is com.example.arch.base.data.ApiResponse.Error -> {
                    _userDetail.value = com.example.arch.base.data.UiState.Error(result.errorMessage)
                }
                is com.example.arch.base.data.ApiResponse.Success -> {
                    _userDetail.value = com.example.arch.base.data.UiState.Success(result.data)
                }
            }
        }
    }
}