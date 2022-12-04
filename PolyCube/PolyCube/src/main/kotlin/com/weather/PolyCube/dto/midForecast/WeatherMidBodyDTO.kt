package com.weather.PolyCube.dto.midForecast

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown=true)
data class WeatherMidBodyDTO(
    val dataType : String?,
    val items : WeatherMidItemsDTO?,
) {
    constructor() : this(null,null)
}