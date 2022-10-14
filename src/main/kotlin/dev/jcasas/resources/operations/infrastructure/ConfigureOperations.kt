package dev.jcasas.resources.operations.infrastructure

import dev.jcasas.plugins.Database
import dev.jcasas.resources.operations.controller.OperationsController
import dev.jcasas.resources.operations.domain.service.OperationsService
import io.ktor.server.application.Application

fun Application.configureOperations() {
    val operationsRepository = MongoDbOperationsRepository(Database.instance)
    val operationsService = OperationsService(operationsRepository)
    OperationsController(this, operationsService)
}