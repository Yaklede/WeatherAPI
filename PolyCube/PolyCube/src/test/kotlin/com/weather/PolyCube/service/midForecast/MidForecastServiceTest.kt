package com.weather.PolyCube.service.midForecast

import com.weather.PolyCube.domain.MidForecast
import com.weather.PolyCube.dto.midForecast.WeatherMidItemDTO
import com.weather.PolyCube.dto.midForecast.WeatherMidRequest
import com.weather.PolyCube.repository.baseLocation.BaseLocationRepository
import com.weather.PolyCube.repository.locationCode.LocationCodeRepository
import com.weather.PolyCube.repository.midForecast.MidForecastRepository
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
class MidForecastServiceTest @Autowired constructor(
    private val midForecastService: MidForecastService,
    private val midForecastRepository: MidForecastRepository,
    private val locationRepository: LocationCodeRepository,
) {
    val city = "서울특별시"
    val tmFc = "202212060600"

    @Test
    @DisplayName("DB에 데이터가 있다면 DB에서 가져옴")
    fun getWeatherApiDbDataO() {
        val regId = locationRepository.findByCity(city)?.code
        val request : WeatherMidRequest = WeatherMidRequest(city,regId,tmFc)

        val oldData = midForecastRepository.findWeatherByRegIdAndTmFc(regId,tmFc)
        midForecastService.getWeather(request)
        val newData = midForecastRepository.findWeatherByRegIdAndTmFc(regId,tmFc)

        Assertions.assertThat(oldData).isEqualTo(newData)
    }
    @Test
    @DisplayName("DB에 데이터가 없다면 OPENAPI 호출 후 가져옴")
    fun getWeatherApiDbDataX() {
        val otherCity = "대전"

        val regId = locationRepository.findByCity(city)?.code
        val newRegId = locationRepository.findByCity(otherCity)?.code
        val request : WeatherMidRequest = WeatherMidRequest(otherCity,newRegId,tmFc)

        val oldData = midForecastRepository.findWeatherByRegIdAndTmFc(regId,tmFc)
        val newData = midForecastService.getWeather(request)

        Assertions.assertThat(regId).isNotEqualTo(newRegId)
        Assertions.assertThat(oldData).isNotEqualTo(newData)
    }
}