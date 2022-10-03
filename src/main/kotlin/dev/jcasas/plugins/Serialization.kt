package dev.jcasas.plugins

import io.ktor.serialization.gson.gson
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.get

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        gson()
    }
}
