package com.weather.PolyCube.repository.shortForecast

import com.querydsl.jpa.impl.JPAQueryFactory
import com.weather.PolyCube.domain.QShortForecast.shortForecast
import com.weather.PolyCube.domain.ShortForecast
import com.weather.PolyCube.dto.weather.WeatherRequest

class ShortForecastCustomRepositoryImpl(
    private val queryFactory: JPAQueryFactory)
    : ShortForecastCustomRepository {

    override fun findWeatherByRequest(request: WeatherRequest?): List<ShortForecast>? {
        //기본 데이터 시간을 0500시로 고정 -> api 데이터 0500만 호출 됌
        request?.changeShortBaseTime()
        return queryFactory.select(shortForecast)
            .from(shortForecast)
            .where(shortForecast.nx.eq(request?.nx)
                .and(shortForecast.ny.eq(request?.ny)
                    .and(shortForecast.baseDate.eq(request?.baseDate)
                        .and(shortForecast.baseTime.eq(request?.baseTime)))))
            .fetch()
    }
}