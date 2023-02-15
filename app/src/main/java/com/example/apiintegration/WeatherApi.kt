package com.example.apiintegration

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/weather")
    fun getCurrentWeatherData(
        @Query("q") location: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String
    ): Call<WeatherResponse>
}