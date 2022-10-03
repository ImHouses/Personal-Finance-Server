package dev.jcasas.resources.operations.controller

import dev.jcasas.resources.operations.controller.mapper.toParam
import dev.jcasas.resources.operations.controller.mapper.toResponse
import dev.jcasas.resources.operations.controller.payload.OperationPayload
import dev.jcasas.resources.operations.domain.model.Operation
import dev.jcasas.resources.operations.domain.service.OperationsService
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

class OperationsController(
    application: Application,
    private val operationsService: OperationsService,
) {

    init {
        application.routing() {
            route(BASE_ROUTE) {
                get {
                    call.respond(getOperations())
                }

                post {
                    val result = add(call)
                    with (call) {
                        respond(result.toResponse())
                    }
                }
            }
        }
    }

    private suspend fun getOperations(): List<Operation> = operationsService.getAll()


    private suspend fun add(call: ApplicationCall): Operation {
        val operationPayload = call.receive<OperationPayload>()
        return operationsService.add(operationPayload.toParam())
    }

    companion object {
        const val BASE_ROUTE = "/operations"
    }
}