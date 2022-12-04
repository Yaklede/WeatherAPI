package com.weather.PolyCube.dto.midForecast

data class WeatherMidItemsDTO(
    val item: List<WeatherMidItemDTO>?,
    val numOfRows: Int?,
    val pageNo: Int?,
    val totalCount: Int?,
) {
    constructor() : this(null,null,null,null)
}