package com.weather.PolyCube.repository.midForecast

import com.weather.PolyCube.domain.MidForecast
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MidForecastRepository : JpaRepository<MidForecast,Long> {
    fun findWeatherByRegIdAndTmFc(regId: String?,tmFc: String?) : MidForecast?
}