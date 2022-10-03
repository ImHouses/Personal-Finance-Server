package dev.jcasas.resources.operations.domain.mapper

import dev.jcasas.resources.operations.domain.model.Operation
import dev.jcasas.resources.operations.domain.service.OperationsService
import java.time.Clock
import java.time.Instant
import java.util.Date

fun OperationsService.Param.Add.toOperation(): Operation = Operation(
    name = name,
    type = if (type == "EXPENSE") Operation.Type.EXPENSE else Operation.Type.INCOME,
    category = category,
    amount = amount,
    date = Date.from(Instant.now(Clock.systemUTC()))
)
