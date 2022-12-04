package com.weather.PolyCube.repository.ultraShortForecast

import com.querydsl.jpa.impl.JPAQueryFactory
import com.weather.PolyCube.domain.QUltraShortForecast.ultraShortForecast
import com.weather.PolyCube.domain.UltraShortForecast
import com.weather.PolyCube.dto.weather.WeatherRequest

class UltraShortForecastCustomRepositoryImpl(
    private val queryFactory : JPAQueryFactory
) : UltraShortForecastCustomRepository {
    override fun findWeatherByRequest(request: WeatherRequest): List<UltraShortForecast>? {
        //시간 데이터 30분 단위로 변경
        request.changeBaseTime(request.baseTime)
        return queryFactory.select(ultraShortForecast)
            .from(ultraShortForecast)
            .where(
                ultraShortForecast.nx.eq(request?.nx)
                    .and(ultraShortForecast.ny.eq(request?.ny)
                        .and(ultraShortForecast.baseDate.eq(request?.baseDate)
                            .and(ultraShortForecast.baseTime.eq(request?.baseTime))))
            )
            .fetch()
    }

}