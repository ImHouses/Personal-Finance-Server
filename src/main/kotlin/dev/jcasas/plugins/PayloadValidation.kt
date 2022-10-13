package dev.jcasas.plugins

import dev.jcasas.resources.operations.controller.payload.configureOperationsValidations
import io.ktor.server.application.*
import io.ktor.server.application.Application
import io.ktor.server.plugins.requestvalidation.RequestValidation

fun Application.configureValidation() {
    install(RequestValidation) {
        configureOperationsValidations()
    }
}