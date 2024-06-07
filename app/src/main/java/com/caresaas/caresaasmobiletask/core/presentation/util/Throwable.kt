package com.caresaas.caresaasmobiletask.core.presentation.util

import java.io.IOException
import java.net.MalformedURLException
import java.net.SocketTimeoutException

fun Throwable.processNetworkError(): String {
    this.let {
        this.printStackTrace()
        val message: String = when (this) {
            is SocketTimeoutException -> "looks like this took longer to load, check your internet and try again"
            is MalformedURLException -> "Malformed URL, Report to Developer"
            is IOException -> "Seems you have a bad Internet Connection, please check and try again"
            else -> this.message ?: "Internal Error"
        }
        return message
    }
}