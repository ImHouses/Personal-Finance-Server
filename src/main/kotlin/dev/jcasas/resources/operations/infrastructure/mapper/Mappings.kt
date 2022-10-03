package dev.jcasas.resources.operations.infrastructure.mapper

import dev.jcasas.resources.operations.domain.model.Operation
import dev.jcasas.resources.operations.domain.repository.OperationRepository
import dev.jcasas.resources.operations.infrastructure.model.OperationStorageModel

fun OperationRepository.Param.Add.toStorageModel(): OperationStorageModel = OperationStorageModel(
    name = name,
    type = type,
    category = category,
    amount = amount,
    date = date
)

fun OperationStorageModel.toDomainModel(): Operation = Operation(
    id = id.toString(),
    name = name,
    category = category,
    amount = amount,
    date = date,
    type = if (type == "EXPENSE") Operation.Type.EXPENSE else Operation.Type.INCOME
)