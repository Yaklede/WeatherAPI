package com.weather.PolyCube.dto.ShortForecast

data class WeatherHeaderDTO(
    val resultCode : String?,
    val resultMsg : String?,
) {
    constructor() : this(null,null)
}