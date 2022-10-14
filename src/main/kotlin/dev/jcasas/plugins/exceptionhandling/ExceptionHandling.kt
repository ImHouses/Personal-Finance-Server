package dev.jcasas.plugins.exceptionhandling

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.install
import io.ktor.server.plugins.requestvalidation.RequestValidationException
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond

fun Application.configureExceptionHandling() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            when {
                cause.isBadRequest() -> handleBadRequest(call, cause.message)
                cause is RequestValidationException -> handleRequestValidationException(call, cause)
                else -> handleUnknownError(call, cause)
            }
        }
    }
}

private suspend fun handleBadRequest(call: ApplicationCall, message: String?) {
    call.respond(
        HttpStatusCode.BadRequest,
        ApiError(message ?: "Request impossible to fulfill.")
    )
}

private suspend fun handleRequestValidationException(call: ApplicationCall, exception: RequestValidationException) {
    call.respond(
        HttpStatusCode.BadRequest,
        ApiError(exception.reasons.joinToString())
    )
}

private suspend fun handleUnknownError(call: ApplicationCall, throwable: Throwable) {
    call.respond(HttpStatusCode.InternalServerError, ApiError(throwable.stackTraceToString()))
}
