package com.weather.PolyCube.service.shortForecast

import com.weather.PolyCube.domain.BaseLocation
import com.weather.PolyCube.domain.ShortForecast
import com.weather.PolyCube.dto.weather.WeatherRequest
import com.weather.PolyCube.repository.baseLocation.BaseLocationRepository
import com.weather.PolyCube.repository.shortForecast.ShortForecastRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.AssertionsForInterfaceTypes
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.ResponseEntity
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional
import javax.xml.ws.Response

@SpringBootTest
@Transactional
class ShortForecastServiceTest @Autowired constructor(
    private val shortForecastService: ShortForecastService,
    private val baseLocationRepository: BaseLocationRepository,
    private val shortForecastRepository: ShortForecastRepository

) {
    val baseDate = "20221204"
    val baseTime = "0500"
    val nx = "60"
    val ny = "127"
    @BeforeEach()
    fun init(){
        shortForecastRepository.save(ShortForecast(baseDate,baseTime,"test","테스트데이터","0730",nx,ny))
    }


    @Test
    @DisplayName("DB에 데이터가 있다면 DB에서 가져옴")
    fun getWeatherApiDbDataO() {
        val request : WeatherRequest = WeatherRequest("서울특별시","종로구","사직동",baseTime,baseDate,nx,ny)

        val oldData = shortForecastRepository.findWeatherByRequest(request)
        val newData = shortForecastService.getWeather(request)

        Assertions.assertThat(oldData).isEqualTo(newData)

    }
    @Test
    @DisplayName("DB에 데이터가 없다면 OPENAPI 호출 후 가져옴")
    fun getWeatherApiDbDataX() {
        val otherDate = "20221206"
        val request : WeatherRequest = WeatherRequest("서울특별시","종로구","사직동",baseTime,otherDate,nx,ny)

        val oldData = shortForecastRepository.findWeatherByRequest(request)
        val newData = shortForecastService.getWeather(request)

        Assertions.assertThat(oldData).isNotEqualTo(newData)

    }


}