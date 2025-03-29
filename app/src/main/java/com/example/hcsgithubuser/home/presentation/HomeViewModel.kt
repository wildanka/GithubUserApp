package com.example.hcsgithubuser.home.presentation

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.hcsgithubuser.base.data.ApiResponse
import com.example.hcsgithubuser.base.presentation.BaseViewModel
import com.example.hcsgithubuser.framework.AppUtility
import com.example.hcsgithubuser.framework.Event
import com.example.hcsgithubuser.home.data.local.GithubUserDatabase
import com.example.hcsgithubuser.home.domain.GithubUserUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val githubUserUseCase: GithubUserUseCase, private val database: GithubUserDatabase) : BaseViewModel() {

    private var coroutineJob: Job? = null
    var page = 0
    var lastId : Int? = null

    private val _isLoading = MutableStateFlow(false)  // Internal mutable state
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _errorMessage = MutableLiveData<Event<String>>()
    val errorMessage: LiveData<Event<String>> = _errorMessage

    val items = Pager(
        PagingConfig(50)
    ){
        database.githubUserDao.pagingSource()
    }.flow


    override fun initialisation(savedInstanceState: Bundle?) {
        //TODO : leave for initialisation scope
        observeData()
        loadUserDataRemotely()
    }

    private fun observeData(){

    }

    fun refreshList(){
        _isLoading.value = true
        if(AppUtility.isNetworkAvailable()){
            page = 0
            lastId = null
            coroutineJob?.cancel()
            _isLoading.value = false
            loadUserDataRemotely()
        }else{
            _isLoading.value = false
            _errorMessage.postValue(Event("No network connection"))
        }
    }


    fun loadUserDataRemotely() {
        if(AppUtility.isNetworkAvailable()){
            Log.d("WLDN HVM", "triggerSomething have network: ")
            fetchGithubUser()
        }else{
            _isLoading.value = false
            _errorMessage.postValue(Event("No network connection"))
        }
    }

    private fun fetchGithubUser(){
        coroutineJob = viewModelScope.launch {
            if(isLoading.value){
                return@launch
            }
            _isLoading.value = true
            page++
            when (val result = githubUserUseCase.fetchUserList(page, lastId)) {
                is ApiResponse.Success -> {
                    lastId = result.data.lastOrNull()?.id
                    _isLoading.value = false
                }
                is ApiResponse.Empty -> {
                    _isLoading.value = false
                }
                is ApiResponse.Error -> {
                    page--
                    _isLoading.value = false
                }
            }
        }
    }
}