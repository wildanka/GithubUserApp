package com.example.detail.presentation.component

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.core.network.response.GithubUserDto

@Composable
fun FollowingList() {
    Box {
        Text("Following List")
    }
    /*LaunchedEffect(followers.loadState) {
        Log.d("WLDN FLS", "LoadState: ${followers.loadState}")
    }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(followers.itemCount) { index ->
            val follower = followers[index]
            if (follower != null) {
                GithubUserItem(follower)
            }
        }

        item {
            when (followers.loadState.append) {
                is LoadState.Loading -> {
                    CircularProgressIndicator(Modifier.padding(16.dp))
                }
                is LoadState.Error -> {

                    Text("Failed to load more", Modifier.padding(16.dp))
                }
                else -> {}
            }
        }
    }*/
}

