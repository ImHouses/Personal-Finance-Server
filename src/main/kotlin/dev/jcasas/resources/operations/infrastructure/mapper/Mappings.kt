package dev.jcasas.resources.operations.infrastructure.mapper

import dev.jcasas.resources.operations.domain.model.Operation
import dev.jcasas.resources.operations.domain.repository.OperationRepository
import dev.jcasas.resources.operations.infrastructure.model.OperationStorageModel
import org.bson.types.ObjectId
import org.litote.kmongo.Id
import org.litote.kmongo.id.toId
import org.litote.kmongo.newId
import org.litote.kmongo.toId

fun Operation.toStorageModel(): OperationStorageModel = OperationStorageModel(
    id = id?.let { ObjectId(id) }?.toId() ?: newId(),
    name = name,
    type = if (type == Operation.Type.EXPENSE) "EXPENSE" else "INCOME",
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
