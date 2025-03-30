package com.example.detail.presentation.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.common.presentation.ui.theme.HcsGithubUserTheme
import com.example.detail.presentation.component.ProfileSection
import com.example.detail.presentation.component.StatItem
import com.example.detail.presentation.screen.tabItems

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
    selectedTabIndex: Int = 0,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    onTabSelected: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp) // Extra space for scrolling
    ) {
        item {
            // Back Button
            IconButton(
                onClick = { onBackClick() },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        // Profile Section
        item {
            ProfileSection(
                name = name,
                location = location,
                avatarUrl = avatarUrl,
                bio = bio,
                email = email,
                twitterUsername = twitterUsername,
            )
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        // Stats Section
        item {
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

        item { Spacer(modifier = Modifier.height(16.dp)) }

        item {
            Column(modifier = Modifier.fillMaxSize()) {
                TabRow(selectedTabIndex = selectedTabIndex) {
                    tabItems.forEachIndexed { index, tabItem ->
                        Tab(
                            selected = index == selectedTabIndex,
                            onClick = { onTabSelected(index) },
                            text = { Text(text = tabItem.title) },
                            icon = {
                                Icon(
                                    imageVector = if (index == selectedTabIndex) {
                                        tabItem.selectedIcon
                                    } else {
                                        tabItem.unselectedIcon
                                    },
                                    contentDescription = tabItem.title,
                                )
                            }
                        )
                    }
                }
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f) // Make sure it fills remaining space
                ) { index ->
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = tabItems[index].title)
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun UserDetailContentPreview() {
    HcsGithubUserTheme {
        var selectedTabIndex by remember {
            mutableIntStateOf(
                0
            )
        }
        val pagerState = rememberPagerState {
            tabItems.size
        }
        LaunchedEffect(selectedTabIndex) {
            pagerState.animateScrollToPage(selectedTabIndex)
        }

        UserDetailContent(
            name = "-",
            followers = 0,
            following = 0,
            publicRepo = 0,
            company = "-",
            location = "-",
            avatarUrl = "-",
            bio = "-",
            email = "-",
            twitterUsername = "-",
            selectedTabIndex = selectedTabIndex,
            pagerState = pagerState,
            onBackClick = {},
            onTabSelected = { selectedPosition ->
                selectedTabIndex = selectedPosition
            }
        )
    }
}