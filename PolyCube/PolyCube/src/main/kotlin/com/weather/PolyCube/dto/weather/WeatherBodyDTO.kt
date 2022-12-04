package com.weather.PolyCube.dto.weather

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown=true)
data class WeatherBodyDTO(
    val dataType : String?,
    val items : WeatherItemsDTO?,
) {
    constructor() : this(null,null)
}