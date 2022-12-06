package com.weather.PolyCube.repository.locationCode

import com.weather.PolyCube.domain.LocationCode
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class LocationCodeRepositoryTest @Autowired constructor(
    private val locationCodeRepository: LocationCodeRepository,
) {
    @Test
    fun findByCity() {
        //given
        val locationCode = LocationCode("안산","test")
        locationCodeRepository.save(locationCode)
        //when
        val result = locationCodeRepository.findByCity("안산")
        //then
        assertThat(result?.code).isEqualTo("test")
    }
}