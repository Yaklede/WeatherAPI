package com.weather.PolyCube.controller

import com.weather.PolyCube.domain.ShortForecast
import com.weather.PolyCube.dto.weather.WeatherRequest
import com.weather.PolyCube.service.shortForecast.ShortForecastService
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RestController

@RestController
class ShortForecastController(
    private val shortForecastService: ShortForecastService
) {
    @GetMapping(value = ["/weather/getShort"], produces = ["application/json"])
    fun getWeather(@ModelAttribute request : WeatherRequest) : ResponseEntity<List<ShortForecast>>? {
        val header = HttpHeaders()
        header.contentType = MediaType.APPLICATION_JSON
        val weatherApi = shortForecastService.getWeather(request)
        return ResponseEntity.ok().body(weatherApi)
    }
}