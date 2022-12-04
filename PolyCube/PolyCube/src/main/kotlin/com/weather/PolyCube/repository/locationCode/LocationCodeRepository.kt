package com.weather.PolyCube.repository.locationCode

import com.weather.PolyCube.domain.LocationCode
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LocationCodeRepository : JpaRepository<LocationCode,Long> {
    fun findByCity(city : String?) : LocationCode?
}