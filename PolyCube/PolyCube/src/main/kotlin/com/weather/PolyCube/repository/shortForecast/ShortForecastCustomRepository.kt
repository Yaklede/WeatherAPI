package com.weather.PolyCube.repository.shortForecast

import com.weather.PolyCube.domain.ShortForecast
import com.weather.PolyCube.dto.weather.WeatherRequest

interface ShortForecastCustomRepository {
    fun findWeatherByRequest(request: WeatherRequest?) : List<ShortForecast>?
}