package com.weather.PolyCube.repository.baseLocation

import com.weather.PolyCube.domain.BaseLocation
import org.springframework.data.jpa.repository.JpaRepository

interface BaseLocationRepository : JpaRepository<BaseLocation,Long>, BaseLocationCustomRepository {
}