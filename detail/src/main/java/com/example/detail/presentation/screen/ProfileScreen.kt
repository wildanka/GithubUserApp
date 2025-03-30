package com.example.detail.presentation.screen

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.arch.base.data.UiState
import com.example.detail.domain.model.TabItem
import com.example.detail.presentation.content.UserDetailContent
import com.example.detail.presentation.viewmodel.ProfileDetailViewModel
import org.koin.compose.viewmodel.koinViewModel

val tabItems = listOf(
    TabItem(
        title = "Followers",
        unselectedIcon = Icons.Outlined.RemoveRedEye,
        selectedIcon = Icons.Filled.RemoveRedEye
    ),
    TabItem(
        title = "Following",
        unselectedIcon = Icons.Outlined.Person,
        selectedIcon = Icons.Filled.Person
    )
)

@Composable
fun ProfileScreen(
    userId: Int,
    username: String,
    modifier: Modifier = Modifier,
    viewModel: ProfileDetailViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState {
        tabItems.size
    }
    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if(!pagerState.isScrollInProgress) {
            selectedTabIndex = pagerState.currentPage
        }
    }

    viewModel.userDetail.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                //TODO : show shimmerlayout effect
                viewModel.checkDetailFromDb(userId, username)
                UserDetailContent(
                    loginUsername = "loginUsername",
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
                    onBackClick = onBackClick,
                    onTabSelected = { selectedPosition ->
                        selectedTabIndex = selectedPosition
                    }
                )
            }

            is UiState.Success -> {
                val data = uiState.data
                UserDetailContent(
                    loginUsername = data.login.orEmpty(),
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
                    selectedTabIndex = selectedTabIndex,
                    pagerState = pagerState,
                    onBackClick = onBackClick,
                    onTabSelected = { selectedPosition ->
                        selectedTabIndex = selectedPosition
                    }
                )
            }
            is UiState.Error -> {
                //TODO : showError
            }
        }
    }
}


