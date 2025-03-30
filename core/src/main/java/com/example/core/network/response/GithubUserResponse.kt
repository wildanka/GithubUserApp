package com.example.core.network.response

import com.squareup.moshi.Json

data class GithubUserResponse(
	@Json(name = "total_count")
	val totalCount : Int? = null,

	@Json(name = "incomplete_results")
	val incompleteResults : Boolean? = false,

	@Json(name="items")
	val items: List<GithubUserDto>? = null
)

