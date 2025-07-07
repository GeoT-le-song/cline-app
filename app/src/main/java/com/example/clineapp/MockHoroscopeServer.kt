package com.example.clineapp

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

@Serializable
data class MockHoroscopeDetail(
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

@Serializable
data class MockHoroscope(
    val horoscope: List<MockHoroscopeDetail>
)

fun main() {
    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            json()
        }
        routing {
            get("/api/v1/horoscope/today/{date}") {
                val date = call.parameters["date"]
                println("Received request for date: $date")
                val mockResponse = MockHoroscope(
                    horoscope = listOf(
                        MockHoroscopeDetail(
                            rank = 1,
                            sign = "Aquarius",
                            content = "Today is your lucky day!",
                            item = "Book",
                            color = "Blue",
                            total = 5,
                            love = 5,
                            money = 5,
                            job = 5
                        )
                    )
                )
                call.respond(mockResponse)
            }
        }
    }.start(wait = true)
}
