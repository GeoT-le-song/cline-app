package com.example.clineapp

import kotlinx.serialization.Serializable

@Serializable
data class HoroscopeDetailDto(
    val rank: Int,
    val sign: String,
    val content: String,
    val item: String,
    val color: String,
    val total: Int,
    val love: Int,
    val money: Int,
    val job: Int
)
