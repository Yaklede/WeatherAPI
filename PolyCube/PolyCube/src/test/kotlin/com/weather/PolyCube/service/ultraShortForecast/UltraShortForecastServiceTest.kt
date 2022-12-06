package com.weather.PolyCube.service.ultraShortForecast

import com.weather.PolyCube.domain.ShortForecast
import com.weather.PolyCube.domain.UltraShortForecast
import com.weather.PolyCube.dto.weather.WeatherRequest
import com.weather.PolyCube.repository.baseLocation.BaseLocationRepository
import com.weather.PolyCube.repository.ultraShortForecast.UltraShortForecastRepository
import com.weather.PolyCube.service.shortForecast.ShortForecastService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class UltraShortForecastServiceTest @Autowired constructor(
    private val ultraShortForecastService: ShortForecastService,
    private val ultraShortForecastRepository: UltraShortForecastRepository,
    private val baseLocationRepository: BaseLocationRepository,
) {
    val baseDate = "20221206"
    val baseTime = "0630"
    val nx = "60"
    val ny = "127"
    @BeforeEach()
    fun init(){
        ultraShortForecastRepository.save(UltraShortForecast(baseDate,baseTime,"test","테스트데이터","0730",nx,ny))
    }
    @Test
    @DisplayName("DB에 데이터가 있다면 DB에서 가져옴")
    fun getWeatherApiDbDataO() {
        val request : WeatherRequest = WeatherRequest("서울특별시","종로구","사직동",baseTime,baseDate,nx,ny)

        val oldData = ultraShortForecastRepository.findWeatherByRequest(request)
        ultraShortForecastService.getWeather(request)
        val newData = ultraShortForecastRepository.findWeatherByRequest(request)

        Assertions.assertThat(oldData).isEqualTo(newData)

    }
    @Test
    @DisplayName("DB에 데이터가 없다면 OPENAPI 호출 후 가져옴")
    fun getWeatherApiDbDataX() {
        val otherDate = "20221206"
        val request : WeatherRequest = WeatherRequest("서울특별시","종로구","사직동",baseTime,otherDate,nx,ny)

        val oldData = ultraShortForecastRepository.findWeatherByRequest(request)
        val newData = ultraShortForecastService.getWeather(request)

        Assertions.assertThat(oldData).isNotEqualTo(newData)

    }
}