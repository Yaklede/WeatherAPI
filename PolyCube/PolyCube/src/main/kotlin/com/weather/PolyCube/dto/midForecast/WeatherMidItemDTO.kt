package com.weather.PolyCube.dto.midForecast

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown=true)
data class WeatherMidItemDTO(

    @JsonProperty("regId")
    val regId : String? = null,
    @JsonProperty("rnSt3Am")
    val rnSt3Am : String? = null,
    @JsonProperty("rnSt3Pm")
    val rnSt3Pm : String? = null,
    @JsonProperty("rnSt4Am")
    val rnSt4Am : String? = null,
    @JsonProperty("rnSt4Pm")
    val rnSt4Pm : String? = null,
    @JsonProperty("rnSt5Am")
    val rnSt5Am : String? = null,
    @JsonProperty("rnSt5Pm")
    val rnSt5Pm : String? = null,
    @JsonProperty("rnSt6Am")
    val rnSt6Am : String? = null,
    @JsonProperty("rnSt6Pm")
    val rnSt6Pm : String? = null,
    @JsonProperty("rnSt7Am")
    val rnSt7Am : String? = null,
    @JsonProperty("rnSt7Pm")
    val rnSt7Pm : String? = null,
    @JsonProperty("rnSt8")
    val rnSt8 : String? = null,
    @JsonProperty("rnSt9")
    val rnSt9 : String? = null,
    @JsonProperty("rnSt10")
    val rnSt10 : String? = null,
    @JsonProperty("wf3Am")
    val wf3Am : String? = null,
    @JsonProperty("wf3Pm")
    val wf3Pm : String? = null,
    @JsonProperty("wf4Am")
    val wf4Am : String? = null,
    @JsonProperty("wf4Pm")
    val wf4Pm : String? = null,
    @JsonProperty("wf5Am")
    val wf5Am : String? = null,
    @JsonProperty("wf5Pm")
    val wf5Pm : String? = null,
    @JsonProperty("wf6Am")
    val wf6Am : String? = null,
    @JsonProperty("wf6Pm")
    val wf6Pm : String? = null,
    @JsonProperty("wf7Am")
    val wf7Am : String? = null,
    @JsonProperty("wf7Pm")
    val wf7Pm : String? = null,
    @JsonProperty("wf8")
    val wf8 : String? = null,
    @JsonProperty("wf9")
    val wf9 : String? = null,
    @JsonProperty("wf10")
    val wf10 : String? = null   ,
) {
    constructor() : this(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null)
}