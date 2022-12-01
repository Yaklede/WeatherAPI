package com.weather.PolyCube.dto.ShortForecast

data class ShortForecastRequest(
        val step1: String?,
        val step2: String?,
        val step3: String?,
        val baseTime: String?,
        val baseDate: String?,
        var nx: String? = null,
        var ny: String? = null,
) {
        fun changeNxNy(nx: String, ny:String) {
                this.nx = nx
                this.ny = ny
        }
}