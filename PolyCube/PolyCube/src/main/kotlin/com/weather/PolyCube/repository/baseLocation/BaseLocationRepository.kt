package com.weather.PolyCube.repository.baseLocation

import com.weather.PolyCube.domain.BaseLocation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BaseLocationRepository : JpaRepository<BaseLocation,Long>, BaseLocationCustomRepository {
}