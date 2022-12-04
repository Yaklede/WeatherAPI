package com.weather.PolyCube.dto.weather

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

        fun changeBaseTime(baseTime: String?) {
                val result = baseTime?.split("0")?.toMutableList()
//                result?.forEach {
//                                i -> if(i.isEmpty())
//                }
//                if(result?.get(0)?.isEmpty()!!) {
//                        result?.set(0,"0")
//                }
//                result?.set(2,"3")
//                result?.set(3,"0")
//                this.baseTime = result?.joinToString()?.replace(", ","")
        }
}