package com.example.arch.base.data.mapper

interface Mapper<S, D> {
    fun map(source: S): D
}