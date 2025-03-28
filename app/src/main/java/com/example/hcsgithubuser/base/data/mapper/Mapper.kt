package com.example.hcsgithubuser.base.data.mapper

interface Mapper<S, D> {
    fun map(source: S): D
}