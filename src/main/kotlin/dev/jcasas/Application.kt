package dev.jcasas

import dev.jcasas.plugins.configureSerialization
import dev.jcasas.plugins.configureValidation
import dev.jcasas.plugins.exceptionhandling.configureExceptionHandling
import dev.jcasas.resources.operations.infrastructure.configureOperations
import io.ktor.server.application.Application

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureSerialization()
    configureExceptionHandling()
    configureValidation()
    configureApp()
}

// TODO: Make a refactor to DI.
fun Application.configureApp() {
    configureOperations()
}
