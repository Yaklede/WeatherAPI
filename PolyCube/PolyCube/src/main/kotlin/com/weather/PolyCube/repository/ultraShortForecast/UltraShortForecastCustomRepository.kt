package com.weather.PolyCube.repository.ultraShortForecast

import com.weather.PolyCube.domain.UltraShortForecast
import com.weather.PolyCube.dto.weather.WeatherRequest
import org.springframework.stereotype.Component

interface UltraShortForecastCustomRepository {
    fun findWeatherByRequest(request : WeatherRequest) : List<UltraShortForecast>?
}