package dev.jcasas.resources.operations.domain.service

import dev.jcasas.resources.operations.domain.model.Operation
import dev.jcasas.resources.operations.domain.repository.OperationRepository
import java.math.BigDecimal
import java.time.Clock
import java.time.Instant
import java.util.Date

class OperationsService(private val repository: OperationRepository) {

    suspend fun getAll(): List<Operation> = repository.getAll()

    suspend fun add(param: Param.Add): Operation = repository.add(
        OperationRepository.Param.Add(
            name = param.name,
            type = param.type,
            category = param.category,
            amount = param.amount,
            date = Date.from(Instant.now(Clock.systemUTC()))
        )
    )

    sealed class Param {
        data class Add(
            val name: String,
            val type: String,
            val category: String,
            val amount: BigDecimal
        ) : Param()
    }
}
