package com.weather.PolyCube.dto.weather

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown=true)
data class WeatherItemDTO(
    @JsonProperty("baseDate")
    val baseDate: String? = null,
    @JsonProperty("baseTime")
    val baseTime: String? = null,
    @JsonProperty("category")
    val category: String? = null,
    @JsonProperty("fcstDate")
    val fcstDate: String? = null,
    @JsonProperty("fcstTime")
    val fcstTime: String? = null,
    @JsonProperty("nx")
    val nx: String? = null,
    @JsonProperty("ny")
    val ny: String? = null,
    @JsonProperty("fcstValue")
    val obsrValue: String?,
) {
    constructor() : this(null,null,null,null,null,null,null,null)
}