package com.weather.PolyCube.dto.midForecast

data class WeatherMidRequest(
    val city : String?,
    var regId : String?,
    val tmFc : String?,
) {
    fun changeRedId(regId: String?) {
        this.regId = regId
    }
}