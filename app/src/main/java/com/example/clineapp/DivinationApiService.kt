package com.example.clineapp

import retrofit2.http.GET
import retrofit2.http.Path

interface DivinationApiService {
    @GET("api/v1/horoscope/today/{date}")
    suspend fun getHoroscope(@Path("date") date: String): HoroscopeDto
}
