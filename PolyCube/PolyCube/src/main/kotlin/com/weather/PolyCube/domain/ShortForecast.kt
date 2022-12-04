package com.weather.PolyCube.domain

import com.weather.PolyCube.dto.weather.WeatherItemDTO
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class ShortForecast(
        val baseDate: String? = null,

        val baseTime: String? = null,

        val category: String? = null,

        val fcstDate: String? = null,

        val fcstTime: String? = null,

        val nx: String? = null,

        val ny: String? = null,

        val obsrValue: String? = null,
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null
) {
        constructor(request: WeatherItemDTO) : this(request.baseDate,request.baseTime,request.category,request.fcstDate,request.fcstTime,request.nx,request.ny,request.obsrValue)
}