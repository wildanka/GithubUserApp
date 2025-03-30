package com.example.hcsgithubuser.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arch.base.data.ApiResponse
import com.example.arch.base.data.UiState
import com.example.hcsgithubuser.framework.AppUtility
import com.example.hcsgithubuser.framework.Event
import com.example.common.data.remote.response.GithubUserDto
import com.example.hcsgithubuser.search.domain.SearchUserUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class SearchViewModel(private val searchUserUseCase: SearchUserUseCase): ViewModel() {
    private val _query = MutableStateFlow("")
    private var page = 0
    var maxResultCount = 0

    private val _isLoading = MutableStateFlow(false)  // Internal mutable state
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _errorMessage = MutableLiveData<Event<String>>()
    val errorMessage: LiveData<Event<String>> = _errorMessage // TODO : Error handling

    private val _userList = MutableStateFlow<com.example.arch.base.data.UiState<List<GithubUserDto>>>(
        com.example.arch.base.data.UiState.Loading)
    val userList: StateFlow<com.example.arch.base.data.UiState<List<GithubUserDto>>> = _userList

    init {
        observeQuery()
    }

    fun setQuery(input: String) {
        _query.value = input
    }

    private var coroutineJob: Job? = null

    private fun observeQuery() {
        viewModelScope.launch {
            _query
                .debounce(1000) // Wait 500ms after last input
                .distinctUntilChanged() // Ignore duplicate values
                .collectLatest { searchText ->
                    if (searchText.isNotEmpty()) {
                        // Call API or update UI based on searchText
                        refreshList(searchText)
                    }
                }
        }
    }


    private fun refreshList(username: String){
        if(AppUtility.isNetworkAvailable()){
            page = 0
            coroutineJob?.cancel()
            _userList.value = com.example.arch.base.data.UiState.Loading
            searchByUsername(username)
        }else{
            _isLoading.value = false
            _errorMessage.postValue(Event("No network connection"))
        }
    }

    fun searchByUsername(username: String){
        if(isLoading.value){
            return
        }
        coroutineJob = viewModelScope.launch {
            _isLoading.value = true
            page++
            when(val result = searchUserUseCase.searchByUsername(username, page)){
                is com.example.arch.base.data.ApiResponse.Success -> {
                    val currentList = (userList.value as? com.example.arch.base.data.UiState.Success)?.data ?: emptyList()
                    val newList = currentList + result.data.items.orEmpty() // Append new data

                    result.data.totalCount?.let {
                        maxResultCount = it
                    }

                    _userList.value = com.example.arch.base.data.UiState.Success(data = newList)
                    _isLoading.value = false

                }
                is com.example.arch.base.data.ApiResponse.Empty -> {
                    _userList.value = com.example.arch.base.data.UiState.Loading
                    _isLoading.value = false
                }
                is com.example.arch.base.data.ApiResponse.Error -> {
                    _userList.value = com.example.arch.base.data.UiState.Error(errorMessage = result.errorMessage)
                    page--
                    _isLoading.value = false
                }
            }
        }
    }
}