package com.weather.PolyCube.repository.shortForecast

import com.weather.PolyCube.domain.ShortForecast
import com.weather.PolyCube.domain.UltraShortForecast
import com.weather.PolyCube.dto.weather.WeatherRequest
import org.assertj.core.api.AssertionsForInterfaceTypes
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class ShortForecastCustomRepositoryImplTest @Autowired constructor(
    private val shortForecastRepository: ShortForecastRepository,
) {
    @BeforeEach()
    fun init(){
        shortForecastRepository.save(ShortForecast("20221204","0730","test","테스트데이터","0730","60","127"))
    }


    @Test
    fun findWeatherByRequest() {
        val request : WeatherRequest = WeatherRequest("서울특별시","종로구","사직동","0700","20221204","60","127")

        val result = shortForecastRepository.findWeatherByRequest(request)
        AssertionsForInterfaceTypes.assertThat(result?.get(0)?.ny).isEqualTo("127")
        AssertionsForInterfaceTypes.assertThat(result?.get(0)?.nx).isEqualTo("60")
    }
}