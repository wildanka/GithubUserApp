package com.example.hcsgithubuser.home.data.remote.response

import com.squareup.moshi.Json

data class GithubUserResponse(
	@Json(name="GithubUserResponse")
	val githubUserResponse: List<GithubUserDto>? = null
)

