package com.example.clineapp

import kotlinx.serialization.Serializable

@Serializable
data class HoroscopeDto(
    val horoscope: Map<String, List<HoroscopeDetailDto>>
)
