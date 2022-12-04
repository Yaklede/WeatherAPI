package com.weather.PolyCube.repository.midForecastRepository

import com.weather.PolyCube.repository.midForecast.MidForecastRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MidForecastRepositoryTest @Autowired constructor(
    private val midForecastRepository: MidForecastRepository,
) {
    @Test
    fun findWeatherByRegIdAndTmFc() {
        midForecastRepository.findWeatherByRegIdAndTmFc("test","test")
    }
}