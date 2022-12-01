package com.weather.PolyCube.dto.ShortForecast

data class ShortForecastResponse(
    val header : WeatherHeaderDTO?,
    val body : WeatherBodyDTO?,
) {
    //빈 생성자
    constructor() : this(null,null)
}