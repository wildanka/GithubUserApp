package com.example.detail.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.network.GithubService
import com.example.core.network.response.GithubUserDto

class FollowersPagingSource(
    private val apiService: GithubService,
    private val username: String
) : PagingSource<Int, GithubUserDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubUserDto> {
        val page = params.key ?: 1
        return try {
            val response = apiService.getFollowers(username, page, params.loadSize)
            LoadResult.Page(
                data = response.map { it },
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GithubUserDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
