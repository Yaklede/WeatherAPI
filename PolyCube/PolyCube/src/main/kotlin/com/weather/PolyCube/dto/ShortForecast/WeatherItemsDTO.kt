package com.weather.PolyCube.dto.ShortForecast

data class WeatherItemsDTO(
    val item : List<WeatherItemDTO>?,
    val numOfRows : Int?,
    val pageNo : Int?,
    val totalCount : Int?,
) {
    constructor() : this(null,null,null,null)
}