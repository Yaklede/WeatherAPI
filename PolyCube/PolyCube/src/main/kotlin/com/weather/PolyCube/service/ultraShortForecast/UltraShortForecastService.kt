package com.weather.PolyCube.service.ultraShortForecast

import com.fasterxml.jackson.databind.ObjectMapper
import com.weather.PolyCube.domain.UltraShortForecast
import com.weather.PolyCube.dto.weather.WeatherRequest
import com.weather.PolyCube.dto.weather.WeatherResponse
import com.weather.PolyCube.repository.baseLocation.BaseLocationRepository
import com.weather.PolyCube.repository.ultraShortForecast.UltraShortForecastRepository
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponents
import org.springframework.web.util.UriComponentsBuilder
import java.net.URL
import java.net.URLEncoder

@Service
class UltraShortForecastService(
    private val baseLocationRepository: BaseLocationRepository,
    private val ultraShortForecastRepository: UltraShortForecastRepository,
) {
    @Transactional
    fun getWeather(request: WeatherRequest) : List<UltraShortForecast>? {
        setNxNyByRequest(request)
        return getUltraShortForecast(request)
    }

    private fun getUltraShortForecast(request: WeatherRequest): List<UltraShortForecast>? {
        try {
            ultraShortForecastRepository.findWeatherByRequest(request) ?: IllegalArgumentException()
        } catch (e: IllegalArgumentException) {
            return getWeatherApi(request)
        }
        return ultraShortForecastRepository.findWeatherByRequest(request)
    }

    private fun setNxNyByRequest(request: WeatherRequest) {
        val resultBaseLocation = baseLocationRepository.findNxNy(request)
        resultBaseLocation?.nx?.let { request.changeNxNy(it, resultBaseLocation.ny) }
    }

    @Transactional
    fun getWeatherApi(request: WeatherRequest): List<UltraShortForecast>? {
        val (restTemplate, uri: UriComponents, jsonUrl: URL) = createUrlByRequest(request)
        saveUltraShortForecast(restTemplate, jsonUrl)
        return ultraShortForecastRepository.findWeatherByRequest(request)

    }

    private fun saveUltraShortForecast(restTemplate: RestTemplate, jsonUrl: URL) {
        val apiResponse = restTemplate.getForEntity(jsonUrl.toURI(), Map::class.java).body.get("response")
        val objectMapper = ObjectMapper()
        val apiResponseString = objectMapper.writeValueAsString(apiResponse)
        val data = objectMapper.readValue(apiResponseString, WeatherResponse::class.java)
        ultraShortForecastRepository.saveAll(data.body?.items?.item?.map { weatherItemDTO ->
            UltraShortForecast(
                weatherItemDTO
            )
        })
    }

    private fun createUrlByRequest(request: WeatherRequest): Triple<RestTemplate, UriComponents, URL> {
        val url: String = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst"
        val serviceKey: String =
            "mr5bzFw5az+cY7uRgx3KT1gW45Dyzy+1JXQHPi4PcW/4Se5DFW3GxmVpWif3IjBfXYBAWbEPDrACuIwKnI4olA=="


        val restTemplate = RestTemplate()
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        // val entity = HttpEntity<Map<String, Any>>(headers)
        val uri: UriComponents = UriComponentsBuilder.fromHttpUrl(
            url
                    + "?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + URLEncoder.encode(
                serviceKey,
                "UTF-8"
            )
                    + "&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")
                    + "&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(
                "1000",
                "UTF-8"
            )
                    + "&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode(
                "json",
                "UTF-8"
            )
                    + "&" + URLEncoder.encode(
                "base_date",
                "UTF-8"
            ) + "=" + URLEncoder.encode(request.baseDate, "UTF-8")
                    + "&" + URLEncoder.encode(
                "base_time",
                "UTF-8"
            ) + "=" + URLEncoder.encode(request.baseTime, "UTF-8")
                    + "&" + URLEncoder.encode(
                "nx",
                "UTF-8"
            ) + "=" + URLEncoder.encode(request!!.nx, "UTF-8")
                    + "&" + URLEncoder.encode(
                "ny",
                "UTF-8"
            ) + "=" + URLEncoder.encode(request!!.ny, "UTF-8")
        ).build()

        val jsonUrl: URL = URL(uri.toString())
        return Triple(restTemplate, uri, jsonUrl)
    }
}