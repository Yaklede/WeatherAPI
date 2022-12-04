package com.weather.PolyCube.repository.baseLocation

import com.weather.PolyCube.domain.BaseLocation
import com.weather.PolyCube.dto.weather.WeatherRequest

interface BaseLocationCustomRepository {
    fun findNxNy(request: WeatherRequest) : BaseLocation?
}