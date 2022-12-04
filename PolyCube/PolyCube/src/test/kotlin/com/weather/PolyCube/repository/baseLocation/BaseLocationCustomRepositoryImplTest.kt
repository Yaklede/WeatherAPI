package com.weather.PolyCube.repository.baseLocation

import com.weather.PolyCube.dto.weather.WeatherRequest
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class BaseLocationCustomRepositoryImplTest @Autowired constructor(
    private val baseLocationRepository: BaseLocationRepository,
) {

    @Test
    fun findNxNy() {
        val step1 : String = "서울특별시"
        val step2 : String = "종로구"
        val step3 : String = "사직동"
        val request = WeatherRequest(step1, step2, step3, null, null)
        val result = baseLocationRepository.findNxNy(request)

        assertThat(result!!.nx).isEqualTo("60")
        assertThat(result!!.ny).isEqualTo("127")
    }
}