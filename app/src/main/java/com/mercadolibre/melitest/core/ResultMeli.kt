package com.mercadolibre.melitest.core

sealed class ResultMELI<out T> {
    data class Success<T>(val data: T) : ResultMELI<T>()
    data class Error(val exception: Throwable) : ResultMELI<Nothing>()

    fun onSuccess(action: (T) -> Unit): ResultMELI<T> {
        if (this is Success) {
            action(data)
        }
        return this
    }

    fun onFailure(action: (Throwable) -> Unit): ResultMELI<T> {
        if (this is Error) {
            action(exception)
        }
        return this
    }

    companion object {
        fun <T> success(data: T): ResultMELI<T> = Success(data)
        fun error(exception: Throwable): ResultMELI<Nothing> = Error(exception)
    }
}
