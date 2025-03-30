package com.example.hcsgithubuser.detail.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.common.presentation.ui.theme.HcsGithubUserTheme
import com.example.core.common.presentation.util.Const
import com.example.hcsgithubuser.detail.presentation.screen.ProfileScreen

class DetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userId = intent.getIntExtra(Const.USER_ID_EXTRA, 0)
        val username = intent.getStringExtra(Const.USERNAME_EXTRA)

        enableEdgeToEdge()
        setContent {
            HcsGithubUserTheme {
                GithubUserDetailScreen(userId, username.orEmpty())
            }
        }
    }


}

@Composable
fun GithubUserDetailScreen(
    userId: Int,
    username: String,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        ProfileScreen(
            userId = userId,
            username = username,
            modifier = Modifier.padding(paddingValues = innerPadding)
        )
    }
}

@Preview(showBackground = false)
@Composable
fun GithubUserDetailContentPreview() {
    HcsGithubUserTheme {
        GithubUserDetailScreen(18630118, "wildanka")
    }
}