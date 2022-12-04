package com.weather.PolyCube.repository.ultraShortForecast

import com.weather.PolyCube.domain.UltraShortForecast
import com.weather.PolyCube.dto.weather.WeatherRequest
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class UltraShortForecastCustomRepositoryImplTest @Autowired constructor(
    private val ultraShortForecastRepository: UltraShortForecastRepository,
){
    @BeforeEach()
    fun init(){
        ultraShortForecastRepository.save(UltraShortForecast("20221204","0730","test","테스트데이터","0730","60","127"))
    }


    @Test
    fun findWeatherByRequest() {
        val request : WeatherRequest = WeatherRequest("서울특별시","종로구","사직동","0700","20221204","60","127")

        val result = ultraShortForecastRepository.findWeatherByRequest(request)
        assertThat(result?.get(0)?.baseTime).isEqualTo("0730")
    }
}