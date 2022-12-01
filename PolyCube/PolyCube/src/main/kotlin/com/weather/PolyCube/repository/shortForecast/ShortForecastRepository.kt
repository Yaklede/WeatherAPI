package com.weather.PolyCube.repository.shortForecast

import com.weather.PolyCube.domain.ShortForecast
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShortForecastRepository : JpaRepository<ShortForecast,Long>,ShortForecastCustomRepository {
}