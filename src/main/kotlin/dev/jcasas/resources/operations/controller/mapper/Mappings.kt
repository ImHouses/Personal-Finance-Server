package dev.jcasas.resources.operations.controller.mapper

import dev.jcasas.resources.operations.controller.payload.OperationPayload
import dev.jcasas.resources.operations.controller.payload.OperationUpdatePayload
import dev.jcasas.resources.operations.controller.response.OperationResponse
import dev.jcasas.resources.operations.domain.model.Operation
import dev.jcasas.resources.operations.domain.service.OperationsService

fun OperationPayload.toServiceParam(): OperationsService.Param.Add =
    OperationsService.Param.Add(
        name = name,
        type = type,
        category = category,
        amount = amount,
        date = date
    )

fun OperationUpdatePayload.toServiceParam(): OperationsService.Param.Update =
    OperationsService.Param.Update(
        id = id,
        name = name,
        type = type,
        category = category,
        amount = amount,
        date = date
    )

fun Operation.toResponse(): OperationResponse = OperationResponse(
    id = id ?: throw IllegalArgumentException("Operation must have id."),
    name = name,
    type = if (type == Operation.Type.EXPENSE) "EXPENSE" else "INCOME",
    category = category,
    amount = amount,
    date = date
)
