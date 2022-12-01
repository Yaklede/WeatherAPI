package com.weather.PolyCube.repository.shortForecast

import com.weather.PolyCube.domain.BaseLocation
import com.weather.PolyCube.domain.ShortForecast
import com.weather.PolyCube.dto.ShortForecast.ShortForecastRequest

interface ShortForecastCustomRepository {
    fun findByWeather(request: ShortForecastRequest?) : List<ShortForecast>?
}