package dev.jcasas.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.callloging.CallLogging
import io.ktor.server.request.path
import org.slf4j.event.Level

fun Application.configureLogging() {
    install(CallLogging) {
        val isDebug: Boolean = System.getenv("DEBUG")?.toBoolean() ?: true
        val mongoLoggingEnabled: Boolean = System.getenv("MONGO_LOGGING")?.toBoolean() ?: true
        if (!isDebug || !mongoLoggingEnabled) {
            java.util.logging.Logger.getLogger("org.mongodb.driver").level = java.util.logging.Level.OFF
            java.util.logging.Logger.getLogger("org.mongodb.driver.connection").level = java.util.logging.Level.OFF
            java.util.logging.Logger.getLogger("org.mongodb.driver.cluster").level = java.util.logging.Level.OFF
            java.util.logging.Logger.getLogger("org.mongodb").level = java.util.logging.Level.OFF
        }
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }
}