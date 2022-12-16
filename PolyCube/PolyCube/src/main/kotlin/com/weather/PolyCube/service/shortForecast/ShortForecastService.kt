package com.weather.PolyCube.service.shortForecast

import com.fasterxml.jackson.databind.ObjectMapper

import com.weather.PolyCube.domain.ShortForecast
import com.weather.PolyCube.dto.weather.WeatherRequest
import com.weather.PolyCube.dto.weather.WeatherResponse
import com.weather.PolyCube.repository.baseLocation.BaseLocationRepository
import com.weather.PolyCube.repository.shortForecast.ShortForecastRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponents
import org.springframework.web.util.UriComponentsBuilder
import java.net.URL
import java.net.URLEncoder
import kotlin.IllegalArgumentException


@Service
class ShortForecastService(
        private val baseLocationRepository: BaseLocationRepository,
        private val shortForecastRepository: ShortForecastRepository,
        private val restTemplate: RestTemplate,
        private val objectMapper: ObjectMapper,
) {
        @Transactional
        fun getWeather(request: WeatherRequest) : List<ShortForecast>? {
                setNxNyByRequest(request)
                return getShortForecast(request)
        }

        private fun setNxNyByRequest(request: WeatherRequest) {
                val resultBaseLocation = baseLocationRepository.findNxNy(request) ?: throw IllegalArgumentException()
                resultBaseLocation?.nx?.let { request.changeNxNy(it, resultBaseLocation.ny) }
        }

        private fun getShortForecast(request: WeatherRequest) : List<ShortForecast>? {
                try {
                        shortForecastRepository.findWeatherByRequest(request) ?: throw IllegalArgumentException()
                } catch (e: IllegalArgumentException) {
                        getWeatherApi(request)
                }
                return shortForecastRepository.findWeatherByRequest(request)
        }

        @Transactional
        fun getWeatherApi(request: WeatherRequest): List<ShortForecast>? {
                val (restTemplate, uri: UriComponents, jsonUrl: URL) = createUrlByRequest(request)
                println("uri = $uri")
                saveShortForecast(restTemplate, jsonUrl)
                return shortForecastRepository.findWeatherByRequest(request)

        }

        private fun saveShortForecast(restTemplate: RestTemplate, jsonUrl: URL) {
                val apiResponse = restTemplate.getForEntity(jsonUrl.toURI(), Map::class.java).body.get("response")
                val apiResponseString = objectMapper.writeValueAsString(apiResponse)
                val data = objectMapper.readValue(apiResponseString, WeatherResponse::class.java)
                shortForecastRepository.saveAll(data.body?.items?.item?.map { weatherItemDTO ->
                        ShortForecast(
                                weatherItemDTO
                        )
                })
        }

        private fun createUrlByRequest(request: WeatherRequest): Triple<RestTemplate, UriComponents, URL> {
                val url: String = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"
                val serviceKey: String =
                        "mr5bzFw5az+cY7uRgx3KT1gW45Dyzy+1JXQHPi4PcW/4Se5DFW3GxmVpWif3IjBfXYBAWbEPDrACuIwKnI4olA=="

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