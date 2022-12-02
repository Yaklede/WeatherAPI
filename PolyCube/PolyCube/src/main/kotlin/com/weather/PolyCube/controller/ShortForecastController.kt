package com.weather.PolyCube.controller

import com.weather.PolyCube.domain.ShortForecast
import com.weather.PolyCube.dto.ShortForecast.ShortForecastRequest
import com.weather.PolyCube.dto.ShortForecast.ShortForecastResponse
import com.weather.PolyCube.dto.ShortForecast.WeatherItemDTO
import com.weather.PolyCube.service.shortForecast.ShortForecastService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ShortForecastController(
    private val shortForecastService: ShortForecastService
) {
    @GetMapping(value = ["/test/weather"], produces = ["application/json"])
    fun getWeather(@RequestParam("step1") step1:String,
                   @RequestParam("step2") step2:String,
                   @RequestParam("step3")step3:String,
                   @RequestParam("baseTime") baseTime:String,
                   @RequestParam("baseDate") baseDate:String,
                            ) : ResponseEntity<List<ShortForecast>>? {
        val request = ShortForecastRequest(step1,step2,step3,baseTime,baseDate)
        val header = HttpHeaders()
        header.contentType = MediaType.APPLICATION_JSON
        val weatherApi = shortForecastService.getWeather(request)
        return ResponseEntity.ok().body(weatherApi)
    }
}