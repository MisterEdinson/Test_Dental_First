package com.app.dental_first.core

sealed class RequestResult<T> {
    class Success<T>(val data: T) : RequestResult<T>()
    class Error<T>(val message: String) : RequestResult<T>()
}