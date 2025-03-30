package com.example.detail.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.core.common.presentation.ui.theme.HcsGithubUserTheme
import com.example.core.network.response.GithubUserDto

@Composable
fun GithubUserItem(username: String, avatarUrl: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = avatarUrl,
            contentDescription = "Avatar",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = username.orEmpty(), fontWeight = FontWeight.Bold)
    }
}


@Preview
@Composable
private fun GithubUserItemPrev() {
    HcsGithubUserTheme {
        val dto = GithubUserDto(login = "wildanka", avatarUrl = "https://avatars.githubusercontent.com/u/69103?v=4")
        GithubUserItem(username = "wildanka", avatarUrl = "https://avatars.githubusercontent.com/u/69103?v=4")
    }
}