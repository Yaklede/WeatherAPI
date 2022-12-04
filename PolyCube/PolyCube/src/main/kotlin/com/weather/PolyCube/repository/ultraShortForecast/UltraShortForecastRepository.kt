package com.weather.PolyCube.repository.ultraShortForecast

import com.weather.PolyCube.domain.UltraShortForecast
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UltraShortForecastRepository : JpaRepository<UltraShortForecast,Long>,UltraShortForecastCustomRepository {
}