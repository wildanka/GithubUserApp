package com.example.detail.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.arch.base.data.ApiResponse
import com.example.arch.base.data.UiState
import com.example.core.database.entity.GithubUserDetailEntity
import com.example.core.network.response.GithubUserDto
import com.example.detail.domain.DetailUserUseCase
import kotlinx.coroutines.flow.Flow
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

    fun getFollowers(username: String): Flow<PagingData<GithubUserDto>> {
        return detailUserUseCase.getFollowers(username).cachedIn(viewModelScope)
    }


}