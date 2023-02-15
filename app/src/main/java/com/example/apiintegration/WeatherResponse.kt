package com.example.apiintegration

import com.google.gson.annotations.SerializedName

data class WeatherResponse (
    @SerializedName("main")
    val main: Main,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("name")
    val name: String
) {
    data class Main(
        @SerializedName("temp")
        val temperature: Float,
        @SerializedName("humidity")
        val humidity: Int
    )

    data class Weather(
        @SerializedName("description")
        val description: String,
        @SerializedName("icon")
        val icon: String
    )
}
