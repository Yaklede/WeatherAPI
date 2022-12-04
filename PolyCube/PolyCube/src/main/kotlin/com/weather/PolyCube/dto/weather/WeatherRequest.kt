package com.weather.PolyCube.dto.weather

import kotlin.time.Duration.Companion.milliseconds

data class WeatherRequest(
        val step1: String?,
        val step2: String?,
        val step3: String?,
        var baseTime: String?,
        val baseDate: String?,
        var nx: String? = null,
        var ny: String? = null,
) {
        fun changeNxNy(nx: String, ny:String) {
                this.nx = nx
                this.ny = ny
        }
        fun changeShortBaseTime() {
                this.baseTime = "0500"
        }

        fun changeUltraShortBaseTime(baseTime: String?) {
                val result = baseTime?.split("0")?.toMutableList()
                for(i in 0 until result!!.size) {
                        if(result[i].isEmpty()) {
                                result[i] = "0"
                        }
                }
                result?.set(2,"3")
                this.baseTime = result?.joinToString()?.replace(", ","")
        }
}