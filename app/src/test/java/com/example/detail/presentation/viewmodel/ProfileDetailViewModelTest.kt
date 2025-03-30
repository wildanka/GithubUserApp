package com.example.detail.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.example.arch.base.data.ApiResponse
import com.example.arch.base.data.UiState
import com.example.core.database.entity.GithubUserDetailEntity
import com.example.core.network.response.GithubUserDto
import com.example.detail.domain.DetailUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class ProfileDetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var detailUserUseCase: DetailUserUseCase
    private lateinit var viewModel: ProfileDetailViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher) // Set main dispatcher for test
        detailUserUseCase = mock(DetailUserUseCase::class.java) // Mock use case
        viewModel = ProfileDetailViewModel(detailUserUseCase) // Init ViewModel
    }

    @Test
    fun `checkDetailFromDb should set Success state when API returns data`() = runTest {
        val mockUser = GithubUserDetailEntity(id = 1, login = "testuser")
        whenever(detailUserUseCase.getUserDetail(1, "testuser"))
            .thenReturn(ApiResponse.Success(mockUser))

        viewModel.checkDetailFromDb(1, "testuser")
        advanceUntilIdle() // Ensure coroutine completes

        assertEquals(UiState.Success(mockUser), viewModel.userDetail.value)
    }

    @Test
    fun `checkDetailFromDb should set Error state when API returns empty`() = runTest {
        whenever(detailUserUseCase.getUserDetail(1, "testuser"))
            .thenReturn(ApiResponse.Empty)

        viewModel.checkDetailFromDb(1, "testuser")
        advanceUntilIdle()

        assertEquals(UiState.Error("No data found"), viewModel.userDetail.value)
    }

    @Test
    fun `checkDetailFromDb should set Error state when API call fails`() = runTest {
        val errorMessage = "Network error"
        whenever(detailUserUseCase.getUserDetail(1, "testuser"))
            .thenReturn(ApiResponse.Error(errorMessage))

        viewModel.checkDetailFromDb(1, "testuser")
        advanceUntilIdle()

        assertEquals(UiState.Error(errorMessage), viewModel.userDetail.value)
    }

//    @Test
//    fun `getFollowers should return Flow of PagingData`() = runTest {
//        val mockUsers = listOf(GithubUserDto(id = 1, login = "follower1"))
//        val pagingDataFlow = flowOf(PagingData.from(mockUsers))
//        whenever(detailUserUseCase.getFollowers("testuser")).thenReturn(pagingDataFlow)
//
//        val result = viewModel.getFollowers("testuser").collectData() // ✅ Manually collect
//
//        assertEquals(mockUsers, result) // ✅ Compare list contents
//    }

//    @Test
//    fun `getFollowing should return Flow of PagingData`() = runTest {
//        val pagingDataFlow = flowOf(PagingData.from(listOf(GithubUserDto(id = 2, login = "following1"))))
//        whenever(detailUserUseCase.getFollowing("testuser")).thenReturn(pagingDataFlow)
//
//        val resultFlow = viewModel.getFollowing("testuser")
//
//        assertEquals(pagingDataFlow.first(), resultFlow.first()) // Compare emitted data
//    }
}
