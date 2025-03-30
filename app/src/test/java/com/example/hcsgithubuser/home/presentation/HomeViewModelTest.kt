package com.example.hcsgithubuser.home.presentation

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.arch.base.data.ApiResponse
import com.example.arch.framework.AppUtility
import com.example.core.database.GithubUserDatabase
import com.example.core.database.dao.GithubUserDao
import com.example.hcsgithubuser.home.domain.GithubUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.anyInt
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    @Mock
    lateinit var githubUserUseCase: GithubUserUseCase

    @Mock
    lateinit var database: GithubUserDatabase

    @Mock
    lateinit var context: Application

    @Mock
    lateinit var dao: GithubUserDao

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this) // ✅ Initialize Mocks
        Dispatchers.setMain(testDispatcher)

        database = mock()  // ✅ Initialize `database` before using it
        dao = mock()  // ✅ Initialize `dao`
        whenever(database.githubUserDao).thenReturn(dao)  // ✅ Use the mocked `database`

        githubUserUseCase = mock()
        // ✅ Ensure `applicationContext` is not null
        whenever(context.applicationContext).thenReturn(context)

        // ✅ Mock ConnectivityManager correctly
        val connectivityManager = mock<ConnectivityManager>()
        whenever(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connectivityManager)

        // ✅ Mock Network and Capabilities
        val network = mock<Network>()
        val networkCapabilities = mock<NetworkCapabilities>()
        whenever(connectivityManager.activeNetwork).thenReturn(network)
        whenever(connectivityManager.getNetworkCapabilities(network)).thenReturn(networkCapabilities)
        whenever(networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)).thenReturn(true)

        viewModel = HomeViewModel(githubUserUseCase, database, context)
    }

    @Test
    fun `refreshList should update loading state and fetch data when network is available`() = runTest {
        whenever(AppUtility.isNetworkAvailable(context)).thenReturn(true)
        whenever(githubUserUseCase.fetchUserList(anyInt(), anyOrNull())).thenReturn(ApiResponse.Success(emptyList()))

        viewModel.refreshList()
        advanceUntilIdle()

        assertEquals(false, viewModel.isLoading.first())
    }

    @Test
    fun `fetchGithubUser should reset loading state on empty response`() = runTest {
        whenever(githubUserUseCase.fetchUserList(anyInt(), anyOrNull())).thenReturn(ApiResponse.Empty)
        viewModel.loadUserDataRemotely()
        advanceUntilIdle()
        assertEquals(false, viewModel.isLoading.first())
    }

    @Test
    fun `fetchGithubUser should decrement page on error`() = runTest {
        whenever(githubUserUseCase.fetchUserList(anyInt(), anyOrNull())).thenReturn(ApiResponse.Error(
            Exception().toString()
        ))
        viewModel.loadUserDataRemotely()
        advanceUntilIdle()
        assertEquals(0, viewModel.page)
    }
}
