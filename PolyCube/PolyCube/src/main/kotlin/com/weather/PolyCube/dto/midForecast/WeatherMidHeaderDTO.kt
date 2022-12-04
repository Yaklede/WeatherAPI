package com.weather.PolyCube.dto.midForecast

data class WeatherMidHeaderDTO(
    val resultCode : String?,
    val resultMsg : String?,
) {
    constructor() : this(null, null)
}