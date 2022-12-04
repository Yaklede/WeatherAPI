package com.weather.PolyCube.domain

import com.weather.PolyCube.dto.midForecast.WeatherMidItemDTO
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class MidForecast (

    /**
     * wf : 날씨예보 ex) wf3Am 3일뒤 오전 날씨 예보
     * rnSt : 강수확률  ex)rnSt3Am 3일뒤 오전 강수 확률
     * Am : 오전
     * Pm : 오후
     *
     */
    //시간
    val tmFc : String?,
    //위치 코드
    val regId : String?,

    val rnSt3Am : String?,

    val rnSt3Pm : String?,

    val rnSt4Am : String?,

    val rnSt4Pm : String?,

    val rnSt5Am : String?,

    val rnSt5Pm : String?,

    val rnSt6Am : String?,

    val rnSt6Pm : String?,

    val rnSt7Am : String?,

    val rnSt7Pm : String?,

    val rnSt8 : String?,

    val rnSt9 : String?,

    val rnSt10 : String?,

    val wf3Am : String?,

    val wf3Pm : String?,

    val wf4Am : String?,

    val wf4Pm : String?,

    val wf5Am : String?,

    val wf5Pm : String?,

    val wf6Am : String?,

    val wf6Pm : String?,

    val wf7Am : String?,

    val wf7Pm : String?,

    val wf8 : String?,

    val wf9 : String?,

    val wf10 : String?,


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null,
        ) {
    constructor(tmFc :String? ,request : WeatherMidItemDTO) : this(tmFc,request.regId,request.rnSt3Am,request.rnSt3Pm,request.rnSt4Am,request.rnSt4Pm,request.rnSt5Am,request.rnSt5Pm,request.rnSt6Am,request.rnSt6Pm,request.rnSt7Am,request.rnSt7Pm,request.rnSt8,request.rnSt9,request.rnSt10,
        request.wf3Am,request.wf3Pm,request.wf4Am,request.wf4Pm,request.wf5Am,request.wf5Pm,request.wf6Am,request.wf6Pm,request.wf7Am,request.wf7Pm,request.wf8,request.wf9,request.wf10)
}