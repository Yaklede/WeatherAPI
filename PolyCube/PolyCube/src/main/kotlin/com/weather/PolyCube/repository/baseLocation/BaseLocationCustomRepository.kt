package com.weather.PolyCube.repository.baseLocation

import com.weather.PolyCube.domain.BaseLocation
import com.weather.PolyCube.domain.ShortForecast
import com.weather.PolyCube.dto.ShortForecast.ShortForecastRequest

interface BaseLocationCustomRepository {
    fun findNxNy(request: ShortForecastRequest) : BaseLocation?
}