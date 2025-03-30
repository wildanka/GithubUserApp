package com.example.hcsgithubuser.detail.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.common.presentation.ui.theme.HcsGithubUserTheme

@Composable
fun ProfileSection(
    name: String,
    location: String?,
    avatarUrl: String,
    bio: String = "",
    email: String = "",
    twitterUsername: String = "",
    modifier: Modifier = Modifier) {
    // Profile Section
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Image
        AsyncImage(
            model = avatarUrl,
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(80.dp)
                .clip(CircleShape)
                .border(2.dp, Color.White, CircleShape),
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Name and Location
        val locationText = if(location.isNullOrBlank()){
            name
        }else{
            "$name • $location"
        }
        Text(
            text = locationText,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            textAlign = TextAlign.Center

        )

        Spacer(modifier = Modifier.height(4.dp))

        // Description
        if (bio.isNotEmpty() || email.isNotEmpty() || twitterUsername.isNotEmpty()) {
            val text = if(twitterUsername.isNotEmpty() && email.isNotEmpty()){
                "$twitterUsername - $email"
            }else{
                if(twitterUsername.isNotEmpty()){
                    twitterUsername
                }else{
                    email
                }
            }


            Text(
                text = "$text\n$bio",
                color = Color.LightGray,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
        
    }
}

@Preview()
@Composable
private fun ProfileSectionPreview() {
    HcsGithubUserTheme {
        ProfileSection(
            name = "Wildan K",
            location = "Cilegon, Banten, Indonesia",
            avatarUrl = "https://avatars.githubusercontent.com/u/66577?v=4",
            bio = "Tes",
            email = "email",
            twitterUsername = "@twitterUsername",
        )
    }
}