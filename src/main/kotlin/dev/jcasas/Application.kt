package dev.jcasas

import io.ktor.server.application.*
import dev.jcasas.plugins.*
import dev.jcasas.resources.operations.controller.OperationsController
import dev.jcasas.resources.operations.domain.service.OperationsService
import dev.jcasas.resources.operations.infrastructure.MongoDbOperationsRepository
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureSerialization()
    configureRouting()
    configureApp()
}

fun Application.configureApp() {
    val database = with (KMongo.createClient().coroutine) {
        getDatabase("personal_finances")
    }
    val operationsRepository = MongoDbOperationsRepository(database)
    val operationsService = OperationsService(operationsRepository)
    OperationsController(this, operationsService)
}
