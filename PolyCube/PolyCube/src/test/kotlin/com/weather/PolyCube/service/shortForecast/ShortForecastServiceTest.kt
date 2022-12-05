package com.weather.PolyCube.service.shortForecast

import com.weather.PolyCube.domain.ShortForecast
import com.weather.PolyCube.dto.weather.WeatherRequest
import com.weather.PolyCube.repository.baseLocation.BaseLocationRepository
import com.weather.PolyCube.repository.shortForecast.ShortForecastRepository
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.ResponseEntity
import javax.xml.ws.Response

@SpringBootTest
class ShortForecastServiceTest @Autowired constructor(
    private val shortForecastService: ShortForecastService,
    private val baseLocationRepository: BaseLocationRepository

) {
    @Test
    fun getWeather() {

        //given
        val request = WeatherRequest("서울시","종로구","사직동","0500","20221204")
        //when
        val result = shortForecastService.getWeather(request)
        assertThat(result).isNull()
        //then
    }


}