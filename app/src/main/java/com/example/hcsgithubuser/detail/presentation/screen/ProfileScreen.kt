package com.example.hcsgithubuser.detail.presentation.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.arch.base.data.UiState
import com.example.hcsgithubuser.detail.presentation.component.ProfileSection
import com.example.hcsgithubuser.detail.presentation.component.StatItem
import com.example.hcsgithubuser.detail.presentation.viewmodel.ProfileDetailViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProfileScreen(
    userId: Int,
    username: String,
    modifier: Modifier = Modifier,
    viewModel: ProfileDetailViewModel = koinViewModel()
) {
    viewModel.userDetail.collectAsState(initial = com.example.arch.base.data.UiState.Loading).value.let { uiState ->
        when (uiState) {
            is com.example.arch.base.data.UiState.Loading -> {
                //TODO : show shimmerlayout effect
                viewModel.checkDetailFromDb(userId, username)
            }
            is com.example.arch.base.data.UiState.Success -> {
                val data = uiState.data
                UserDetailContent(
                    name = data.name.orEmpty(),
                    followers = data.followers ?: 0,
                    following = data.following ?: 0,
                    publicRepo = data.publicRepos ?: 0,
                    company = data.company.orEmpty(),
                    location = data.location.orEmpty(),
                    avatarUrl = data.avatarUrl.orEmpty(),
                    bio = data.bio.orEmpty(),
                    email = data.email.orEmpty(),
                    twitterUsername = data.twitterUsername.orEmpty(),
                )
            }

            is com.example.arch.base.data.UiState.Error -> {
                //TODO : showError
            }
        }
    }
}


@Composable
fun UserDetailContent(
    name: String,
    followers: Int,
    following: Int,
    publicRepo: Int,
    company: String,
    location: String,
    avatarUrl: String,
    bio: String,
    email: String,
    twitterUsername: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF455A64)) // Dark grayish-green background
    ) {
        // Top App Bar
        IconButton(
            onClick = { /* Handle back press */ },
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Profile Section
        ProfileSection(
            name = name,
            location = location,
            avatarUrl = avatarUrl,
            bio = bio,
            email = email,
            twitterUsername = twitterUsername,
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Stats Section
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(16.dp)),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem(title = "Followers", count = followers)
                StatItem(title = "Following", count = following)
                StatItem(title = "Repository", count = publicRepo)
            }
        }
    }
}
