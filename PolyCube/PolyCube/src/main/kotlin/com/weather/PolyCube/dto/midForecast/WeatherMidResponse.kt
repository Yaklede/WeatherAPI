package com.weather.PolyCube.dto.midForecast

data class WeatherMidResponse(
    val header : WeatherMidHeaderDTO?,
    val body : WeatherMidBodyDTO?,
) {
    constructor() : this(null,null)
}