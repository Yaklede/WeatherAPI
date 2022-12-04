package com.weather.PolyCube.controller

import com.weather.PolyCube.domain.UltraShortForecast
import com.weather.PolyCube.dto.weather.WeatherRequest
import com.weather.PolyCube.service.ultraShortForecast.UltraShortForecastService
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UltraShortForecastController(
    private val ultraShortForecastService: UltraShortForecastService,
) {
    @GetMapping(value = ["/weather/getUltraShort"], produces = ["application/json"])
    fun getWeather(@RequestParam("step1") step1:String,
                   @RequestParam("step2") step2:String,
                   @RequestParam("step3")step3:String,
                   @RequestParam("baseTime") baseTime:String,
                   @RequestParam("baseDate") baseDate:String,
        ) : ResponseEntity<List<UltraShortForecast>>? {
        val request = WeatherRequest(step1,step2,step3,baseTime,baseDate)
        val header = HttpHeaders()
        header.contentType = MediaType.APPLICATION_JSON
        val weatherApi = ultraShortForecastService.getWeather(request)
        return ResponseEntity.ok().body(weatherApi)
    }
}