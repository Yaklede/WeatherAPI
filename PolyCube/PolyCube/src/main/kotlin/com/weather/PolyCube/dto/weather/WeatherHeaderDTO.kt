package com.weather.PolyCube.dto.weather

data class WeatherHeaderDTO(
    val resultCode : String?,
    val resultMsg : String?,
) {
    constructor() : this(null,null)
}