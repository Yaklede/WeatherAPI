package com.weather.PolyCube.dto.weather

data class WeatherResponse(
    val header : WeatherHeaderDTO?,
    val body : WeatherBodyDTO?,
) {
    //λΉ μμ±μ
    constructor() : this(null,null)
}