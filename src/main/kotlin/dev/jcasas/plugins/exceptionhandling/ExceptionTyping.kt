package dev.jcasas.plugins.exceptionhandling

import dev.jcasas.resources.operations.domain.model.OperationNotFound
import io.ktor.server.request.ContentTransformationException

fun Throwable.isBadRequest(): Boolean = ExceptionTypes.badRequestTypes.any { it == this::class }

private object ExceptionTypes {
    val badRequestTypes = listOf(
        OperationNotFound::class,
        IllegalArgumentException::class,
        ContentTransformationException::class,
    )
}