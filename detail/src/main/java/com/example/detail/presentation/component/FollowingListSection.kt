package com.example.detail.presentation.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.core.network.response.GithubUserDto

@Composable
fun FollowingList(following: LazyPagingItems<GithubUserDto>, modifier: Modifier = Modifier) {
    val updatedFollowers = rememberUpdatedState(following)
    if (following.itemCount == 0) {
        Text("No following found!", Modifier.padding(16.dp))
    }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(following.itemCount) { index ->
            val follower = updatedFollowers.value[index]
            if (follower != null) {
                GithubUserItem(follower.login.orEmpty(), follower.avatarUrl.orEmpty())
            }
        }

        item {
            when (following.loadState.append) {
                is LoadState.Loading -> {
                    CircularProgressIndicator(Modifier.padding(16.dp))
                }
                is LoadState.Error -> {
                    Text("Failed to load more", Modifier.padding(16.dp))
                }
                else -> {}
            }
        }
    }
}

