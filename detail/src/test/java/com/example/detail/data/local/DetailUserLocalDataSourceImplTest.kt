package com.example.detail.data.local

import com.example.core.database.dao.GithubUserDetailDao
import com.example.core.database.entity.GithubUserDetailEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailUserLocalDataSourceImplTest {

    @Mock
    private lateinit var dao: GithubUserDetailDao

    private lateinit var dataSource: DetailUserLocalDataSourceImpl

    @Before
    fun setup() {
        dataSource = DetailUserLocalDataSourceImpl(dao)
    }

    @Test
    fun `getUser should return expected user entity`() = runTest {
        // Given
        val userId = 1
        val expectedUser = GithubUserDetailEntity(userId, "testUser", "Test Name", "test@github.com")
        whenever(dao.getOne(userId)).thenReturn(flowOf(expectedUser))

        // When
        val result = dataSource.getUser(userId).first()

        // Then
        assertEquals(expectedUser, result)
    }

    @Test
    fun `saveUser should insert user into database`() = runTest {
        // Given
        val user = GithubUserDetailEntity(1, "testUser", "Test Name", "test@github.com")

        // When
        dataSource.saveUser(user)

        // Then
        verify(dao).insertOne(user)
    }

    @Test
    fun `saveUsers should insert multiple users into database`() = runTest {
        // Given
        val users = listOf(
            GithubUserDetailEntity(1, "user1", "Name1", "email1@github.com"),
            GithubUserDetailEntity(2, "user2", "Name2", "email2@github.com")
        )

        // When
        dataSource.saveUsers(users)

        // Then
        verify(dao).insertAll(users)
    }
}
