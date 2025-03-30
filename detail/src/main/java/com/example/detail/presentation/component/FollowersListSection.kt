package com.example.detail.presentation.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.core.common.presentation.ui.theme.HcsGithubUserTheme
import com.example.core.network.response.GithubUserDto

@Composable
fun FollowersList(followers: LazyPagingItems<GithubUserDto>, modifier: Modifier = Modifier) {
    val updatedFollowers = rememberUpdatedState(followers)
    if (followers.itemCount == 0) {
        Text("No followers found!", Modifier.padding(16.dp))
    }
    LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = 80.dp)) {
        items(followers.itemCount) { index ->
            val follower = updatedFollowers.value[index]
            if (follower != null) {
                GithubUserItem(follower.login.orEmpty(), follower.avatarUrl.orEmpty())
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
    }
}

@Preview
@Composable
private fun FollowersListPreview() {
    HcsGithubUserTheme {
    }
}