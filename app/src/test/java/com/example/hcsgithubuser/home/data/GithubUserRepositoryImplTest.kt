package com.example.hcsgithubuser.home.data

import com.example.arch.base.data.ApiResponse
import com.example.core.database.dao.GithubUserDao
import com.example.core.network.GithubService
import com.example.core.network.response.GithubUserDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class GithubUserRepositoryImplTest {

    private lateinit var repository: GithubUserRepositoryImpl
    private val githubServiceApi = mock(GithubService::class.java)
    private val githubUserDao = mock(GithubUserDao::class.java)

    @Before
    fun setUp() {
        repository = GithubUserRepositoryImpl(githubServiceApi, githubUserDao)
    }

    @Test
    fun `fetchUsers should return Success when API returns data`() = runBlocking {
        // Mock API response
        val mockUsers = listOf(
            GithubUserDto(id = 1, login = "user1"),
            GithubUserDto(id = 2, login = "user2")
        )
        `when`(githubServiceApi.getUsersAt(1, null)).thenReturn(Response.success(mockUsers))

        // Call function
        val result = repository.fetchUsers(1, null)

        // Verify database operations
        verify(githubUserDao).clearTable() // Clear table on page 1
        verify(githubUserDao).insertAll(anyList())

        // Verify result
        assert(result is ApiResponse.Success)
        assertEquals(mockUsers, (result as ApiResponse.Success).data)
    }

    @Test
    fun `fetchUsers should return Empty when API returns null body`() = runBlocking {
        `when`(githubServiceApi.getUsersAt(1, null)).thenReturn(Response.success(null))

        val result = repository.fetchUsers(1, null)

        assert(result is ApiResponse.Empty)
    }

    @Test
    fun `fetchUsers should return Error when API request fails`() = runBlocking {
        `when`(githubServiceApi.getUsersAt(1, null))
            .thenReturn(Response.error(500, "Internal Server Error".toResponseBody("text/plain".toMediaType())))

        val result = repository.fetchUsers(1, null)

        assert(result is ApiResponse.Error)
        assertEquals("Internal Server Error", (result as ApiResponse.Error).errorMessage)
    }
}