package com.example.hcsgithubuser.detail.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hcsgithubuser.base.data.ApiResponse
import com.example.hcsgithubuser.base.data.UiState
import com.example.hcsgithubuser.detail.data.local.entity.GithubUserDetailEntity
import com.example.hcsgithubuser.detail.domain.DetailUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileDetailViewModel(private val detailUserUseCase: DetailUserUseCase) : ViewModel() {
    private val _userDetail: MutableStateFlow<UiState<GithubUserDetailEntity>> =
        MutableStateFlow(UiState.Loading)
    val userDetail: StateFlow<UiState<GithubUserDetailEntity>> get() = _userDetail


    fun checkDetailFromDb(userId: Int, username: String) {
        viewModelScope.launch {
            when(val result = detailUserUseCase.getUserDetail(userId, username)){
                is ApiResponse.Empty -> {
                    _userDetail.value = UiState.Error("No data found")
                }
                is ApiResponse.Error -> {
                    _userDetail.value = UiState.Error(result.errorMessage)
                }
                is ApiResponse.Success -> {
                    _userDetail.value = UiState.Success(result.data)
                }
            }
        }
    }
}