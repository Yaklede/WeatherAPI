package com.weather.PolyCube.controller

import com.weather.PolyCube.domain.MidForecast
import com.weather.PolyCube.dto.midForecast.WeatherMidRequest
import com.weather.PolyCube.service.midForecast.MidForecastService
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MidForecastController(
    private val midForecastService: MidForecastService,
) {
    @GetMapping(value = ["/weather/getMid"], produces = ["application/json"])
    fun getWeather(@RequestParam("city") city:String,
                   @RequestParam("tmFc") tmFc:String,) : ResponseEntity<MidForecast>? {
        val request = WeatherMidRequest(city,null,tmFc)
        val header = HttpHeaders()
        header.contentType = MediaType.APPLICATION_JSON
        val weatherApi = midForecastService.getWeather(request)
        return ResponseEntity.ok().body(weatherApi)
    }
}