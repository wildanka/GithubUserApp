package com.example.hcsgithubuser.search.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    init {
        observeQuery()
    }

    fun setQuery(input: String) {
        _query.value = input
    }


    private fun observeQuery() {
        viewModelScope.launch {
            _query
                .debounce(3000) // Wait 500ms after last input
                .distinctUntilChanged() // Ignore duplicate values
                .collectLatest { searchText ->
                    if (searchText.isNotEmpty()) {
                        Log.d("WLDN SVM Debounce", "Searching for: $searchText")
                        // Call API or update UI based on searchText

                        searchByUsername(searchText)
                    }
                }
        }
    }

    private fun searchByUsername(userName: String) {

    }


}