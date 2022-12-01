package com.weather.PolyCube.service.shortForecast

import com.fasterxml.jackson.databind.ObjectMapper

import com.weather.PolyCube.domain.ShortForecast
import com.weather.PolyCube.dto.ShortForecast.ShortForecastRequest
import com.weather.PolyCube.dto.ShortForecast.ShortForecastResponse
import com.weather.PolyCube.repository.baseLocation.BaseLocationRepository
import com.weather.PolyCube.repository.shortForecast.ShortForecastRepository
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponents
import org.springframework.web.util.UriComponentsBuilder
import java.net.URL
import java.net.URLEncoder


@Service
class ShortForecastService(
        private val baseLocationRepository: BaseLocationRepository,
        private val shortForecastRepository: ShortForecastRepository,
) {

        fun getWeather(request: ShortForecastRequest) : List<ShortForecast>? {
                val resultBaseLocation = baseLocationRepository.findNxNy(request)
                resultBaseLocation?.nx?.let { request.changeNxNy(it,resultBaseLocation.ny) }
                println("nx = ${request.nx} ny = ${request.ny}")
                val weather = shortForecastRepository.findByWeather(request)
                if (weather != null) {
                        if(weather.isEmpty()) {
                                return getWeatherApi(request)
                        }
                }
                return weather
        }

        fun getWeatherApi(request: ShortForecastRequest): List<ShortForecast>? {
                val url: String = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"
                val serviceKey: String =
                        "mr5bzFw5az+cY7uRgx3KT1gW45Dyzy+1JXQHPi4PcW/4Se5DFW3GxmVpWif3IjBfXYBAWbEPDrACuIwKnI4olA=="


                val restTemplate = RestTemplate()
                val headers = HttpHeaders()
                headers.contentType = MediaType.APPLICATION_JSON
                val entity = HttpEntity<Map<String, Any>>(headers)
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

                println("uri = $uri")
                val apiResponse = restTemplate.getForEntity(jsonUrl.toURI() ,Map::class.java).body.get("response")
                val objectMapper = ObjectMapper()
                val apiResponseString = objectMapper.writeValueAsString(apiResponse)
                val data = objectMapper.readValue(apiResponseString,ShortForecastResponse::class.java)
                shortForecastRepository.saveAll(data.body?.items?.item?.map { weatherItemDTO -> ShortForecast(weatherItemDTO) })
                return shortForecastRepository.findByWeather(request)

        }
}