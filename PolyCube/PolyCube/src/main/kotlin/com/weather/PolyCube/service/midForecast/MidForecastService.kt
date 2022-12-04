package com.weather.PolyCube.service.midForecast

import com.fasterxml.jackson.databind.ObjectMapper
import com.weather.PolyCube.domain.MidForecast
import com.weather.PolyCube.domain.ShortForecast
import com.weather.PolyCube.dto.midForecast.WeatherMidRequest
import com.weather.PolyCube.dto.midForecast.WeatherMidResponse
import com.weather.PolyCube.dto.weather.WeatherRequest
import com.weather.PolyCube.dto.weather.WeatherResponse
import com.weather.PolyCube.repository.locationCode.LocationCodeRepository
import com.weather.PolyCube.repository.midForecast.MidForecastRepository
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
class MidForecastService(
    private val locationCodeRepository: LocationCodeRepository,
    private val midForecastRepository: MidForecastRepository,
) {
    @Transactional
    fun getWeather(request: WeatherMidRequest) : MidForecast? {
        val resultLocationCode = locationCodeRepository.findByCity(request.city)
        request.changeRedId(resultLocationCode?.code)
        val weather = midForecastRepository.findWeatherByRegIdAndTmFc(request.regId,request.tmFc)
        if (weather == null) {
            return getWeatherApi(request)
        }
        return weather
    }
    @Transactional
    fun getWeatherApi(request: WeatherMidRequest): MidForecast? {
        val url: String = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst"
        val serviceKey: String =
            "mr5bzFw5az+cY7uRgx3KT1gW45Dyzy+1JXQHPi4PcW/4Se5DFW3GxmVpWif3IjBfXYBAWbEPDrACuIwKnI4olA=="


        val restTemplate = RestTemplate()
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
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
            )       + "&" + URLEncoder.encode("regId","UTF-8") + "=" + URLEncoder.encode(
                request.regId,
                "UTF-8"
            )       + "&" + URLEncoder.encode("tmFc","UTF-8") + "=" + URLEncoder.encode(
                request.tmFc,
                "UTF-8"
            )
        ).build()

        val jsonUrl: URL = URL(uri.toString())

        println("uri = $uri")
        val apiResponse = restTemplate.getForEntity(jsonUrl.toURI() ,Map::class.java).body.get("response")
        val objectMapper = ObjectMapper()
        val apiResponseString = objectMapper.writeValueAsString(apiResponse)
        val data = objectMapper.readValue(apiResponseString, WeatherMidResponse::class.java)
        midForecastRepository.saveAll(data.body?.items?.item?.map{weatherMidItemDTO -> MidForecast(request.tmFc,weatherMidItemDTO)})
        return midForecastRepository.findWeatherByRegIdAndTmFc(request.regId,request.tmFc)
    }
}