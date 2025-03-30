package com.example.hcsgithubuser.detail.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.common.presentation.ui.theme.HcsGithubUserTheme

@Composable
fun StatItem(title: String, count: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = count.toString(), fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
        Text(text = title, fontSize = 14.sp, color = Color.Gray)
    }
}

@Preview(showBackground = true)
@Composable
private fun StatItemPreview() {
    HcsGithubUserTheme {
        StatItem("Following", 5)
    }
}