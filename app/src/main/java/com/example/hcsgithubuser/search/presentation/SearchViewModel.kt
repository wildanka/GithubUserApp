package com.example.hcsgithubuser.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hcsgithubuser.base.data.ApiResponse
import com.example.hcsgithubuser.base.data.UiState
import com.example.hcsgithubuser.framework.AppUtility
import com.example.hcsgithubuser.framework.Event
import com.example.hcsgithubuser.home.data.remote.response.GithubUserDto
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

    private val _userList = MutableStateFlow<UiState<List<GithubUserDto>>>(UiState.Loading)
    val userList: StateFlow<UiState<List<GithubUserDto>>> = _userList

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
            _userList.value = UiState.Loading
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
                is ApiResponse.Success -> {
                    val currentList = (userList.value as? UiState.Success)?.data ?: emptyList()
                    val newList = currentList + result.data.items.orEmpty() // Append new data

                    result.data.totalCount?.let {
                        maxResultCount = it
                    }

                    _userList.value = UiState.Success(data = newList)
                    _isLoading.value = false

                }
                is ApiResponse.Empty -> {
                    _userList.value = UiState.Loading
                    _isLoading.value = false
                }
                is ApiResponse.Error -> {
                    _userList.value = UiState.Error(errorMessage = result.errorMessage)
                    page--
                    _isLoading.value = false
                }
            }
        }
    }
}