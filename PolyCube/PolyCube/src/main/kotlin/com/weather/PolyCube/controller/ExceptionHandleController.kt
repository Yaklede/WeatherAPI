package com.weather.PolyCube.controller

import com.weather.PolyCube.dto.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandleController {

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleHttpRequestMethodNotSupportedException(e : HttpRequestMethodNotSupportedException) : ErrorCode {
        return ErrorCode(HttpStatus.METHOD_NOT_ALLOWED.value(),e.message.toString())
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e : IllegalArgumentException) : ErrorCode {
        return ErrorCode(HttpStatus.NOT_FOUND.value(),e.message.toString())
    }

}