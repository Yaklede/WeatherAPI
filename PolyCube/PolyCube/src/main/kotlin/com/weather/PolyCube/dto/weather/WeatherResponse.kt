package com.weather.PolyCube.dto.weather

data class WeatherResponse(
    val header : WeatherHeaderDTO?,
    val body : WeatherBodyDTO?,
) {
    //빈 생성자
    constructor() : this(null,null)
}