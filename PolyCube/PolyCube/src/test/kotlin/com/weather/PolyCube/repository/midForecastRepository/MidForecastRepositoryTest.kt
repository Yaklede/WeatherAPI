package com.weather.PolyCube.repository.midForecastRepository

import com.weather.PolyCube.repository.midForecast.MidForecastRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class MidForecastRepositoryTest @Autowired constructor(
    private val midForecastRepository: MidForecastRepository,
) {
    @Test
    fun findWeatherByRegIdAndTmFc() {
        midForecastRepository.findWeatherByRegIdAndTmFc("test","test")
    }
}