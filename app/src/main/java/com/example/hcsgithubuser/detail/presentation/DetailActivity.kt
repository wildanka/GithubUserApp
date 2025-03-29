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
import com.example.hcsgithubuser.common.presentation.ui.theme.HcsGithubUserTheme
import com.example.hcsgithubuser.detail.presentation.screen.ProfileScreen

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HcsGithubUserTheme {
                GithubUserDetailScreen()
            }
        }
    }


}

@Composable
fun GithubUserDetailScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        ProfileScreen(modifier = Modifier.padding(paddingValues = innerPadding))
    }
}

@Preview(showBackground = false)
@Composable
fun GithubUserDetailContentPreview() {
    HcsGithubUserTheme {
        GithubUserDetailScreen()
    }
}