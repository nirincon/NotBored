package com.example.notbored.utils

/**
 * Sealed class who Return a Success, or a Failure, and stay in a Loading, while search for the data required.
 */
sealed class Result<out T> {
    class Loading<out T>: Result<T>()
    data class Success<out T>(val data: T): Result<T>()
    data class Failure(val exception: Exception): Result<Nothing>()
}
