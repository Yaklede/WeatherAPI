package com.weather.PolyCube.repository.baseLocation

import com.querydsl.jpa.impl.JPAQueryFactory
import com.weather.PolyCube.domain.BaseLocation
import com.weather.PolyCube.domain.QBaseLocation
import com.weather.PolyCube.domain.QBaseLocation.baseLocation
import com.weather.PolyCube.domain.QShortForecast.shortForecast
import com.weather.PolyCube.domain.ShortForecast
import com.weather.PolyCube.dto.ShortForecast.ShortForecastRequest

class BaseLocationCustomRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
) : BaseLocationCustomRepository {
    override fun findNxNy(request: ShortForecastRequest): BaseLocation? {
        return queryFactory.select(baseLocation)
            .from(baseLocation)
            .where(baseLocation.step1.eq(request.step1).and(baseLocation.step2.eq(request.step2).and(baseLocation.step3.eq(request.step3))))
            .fetchOne();
    }

}