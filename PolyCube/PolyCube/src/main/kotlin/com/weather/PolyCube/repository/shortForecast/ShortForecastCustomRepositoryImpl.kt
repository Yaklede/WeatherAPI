package com.weather.PolyCube.repository.shortForecast

import com.querydsl.jpa.hibernate.HibernateQueryFactory
import com.querydsl.jpa.impl.JPAQueryFactory
import com.weather.PolyCube.domain.BaseLocation
import com.weather.PolyCube.domain.QShortForecast.shortForecast
import com.weather.PolyCube.domain.ShortForecast
import com.weather.PolyCube.dto.ShortForecast.ShortForecastRequest

class ShortForecastCustomRepositoryImpl(
    private val queryFactory: JPAQueryFactory)
    : ShortForecastCustomRepository {

    override fun findByWeather(request: ShortForecastRequest?): List<ShortForecast>? {
        return queryFactory.select(shortForecast)
            .from(shortForecast)
            .where(shortForecast.nx.eq(request?.nx)
                .and(shortForecast.ny.eq(request?.ny)
                    .and(shortForecast.baseDate.eq(request?.baseDate)
                        .and(shortForecast.baseTime.eq(request?.baseTime)))))
            .fetch()
    }
}