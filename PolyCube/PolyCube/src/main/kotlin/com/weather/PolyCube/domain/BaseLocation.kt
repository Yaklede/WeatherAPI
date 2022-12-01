package com.weather.PolyCube.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class BaseLocation(
        val step1: String? = null,
        val step2: String? = null,
        val step3: String? = null,
        val nx: String,
        val ny: String,

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
) {
}