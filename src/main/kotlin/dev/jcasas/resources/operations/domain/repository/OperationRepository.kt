package dev.jcasas.resources.operations.domain.repository

import dev.jcasas.resources.operations.domain.model.Operation

interface OperationRepository {

    suspend fun get(id: String): Operation?

    suspend fun getAll(): List<Operation>

    suspend fun add(operation: Operation): Operation

    suspend fun update(operation: Operation): Operation

    suspend fun remove(id: String)
}
