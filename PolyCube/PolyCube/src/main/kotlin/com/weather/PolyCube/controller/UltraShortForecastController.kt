package com.weather.PolyCube.controller

import com.weather.PolyCube.domain.UltraShortForecast
import com.weather.PolyCube.dto.weather.WeatherRequest
import com.weather.PolyCube.service.ultraShortForecast.UltraShortForecastService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class UltraShortForecastController(
    private val ultraShortForecastService: UltraShortForecastService,
) {
    @GetMapping(value = ["/weather/getUltraShort"], produces = ["application/json"])
    fun getWeather(@ModelAttribute request: WeatherRequest) : ResponseEntity<List<UltraShortForecast>>? {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val weatherApi = ultraShortForecastService.getWeather(request)

        return ResponseEntity.ok()
            .headers(headers)
            .body(weatherApi)
    }
}