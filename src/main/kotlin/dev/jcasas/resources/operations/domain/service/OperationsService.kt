package dev.jcasas.resources.operations.domain.service

import dev.jcasas.resources.operations.domain.model.Operation
import dev.jcasas.resources.operations.domain.model.OperationNotFound
import dev.jcasas.resources.operations.domain.repository.OperationRepository
import java.math.BigDecimal
import java.time.Clock
import java.time.Instant
import java.util.Date
import kotlin.IllegalArgumentException

class OperationsService(private val repository: OperationRepository) {

    suspend fun get(id: String): Operation = repository.get(id) ?: throw OperationNotFound(id)

    suspend fun getAll(): List<Operation> = repository.getAll()

    suspend fun add(param: Param.Add): Operation = repository.add(param.toOperation())

    suspend fun update(param: Param.Update): Operation {
        val savedOperation = repository.get(param.id) ?: throw OperationNotFound(param.id)
        val type = when (param.type) {
            "EXPENSE" -> Operation.Type.EXPENSE
            "INCOME" -> Operation.Type.INCOME
            else -> null
        }
        return repository.update(
            savedOperation.copy(
                name = param.name ?: savedOperation.name,
                type = type ?: savedOperation.type,
                category = param.category ?: savedOperation.category,
                amount = param.amount ?: savedOperation.amount,
                date = param.date ?: savedOperation.date
            )
        )
    }

    private fun Param.Add.toOperation(): Operation {
        val type = when (type) {
            "EXPENSE" -> Operation.Type.EXPENSE
            "INCOME" -> Operation.Type.INCOME
            else -> throw IllegalArgumentException()
        }
        return Operation(
            name = name.takeIf { it.isNotEmpty() } ?: throw IllegalArgumentException(),
            type = type,
            category = category,
            amount = amount,
            date = date ?: Date.from(Instant.now(Clock.systemUTC()))
        )
    }

    sealed class Param {
        data class Add(
            val name: String,
            val type: String,
            val category: String,
            val amount: BigDecimal,
            val date: Date? = null,
        ) : Param()

        data class Update(
            val id: String,
            val name: String? = null,
            val type: String? = null,
            val category: String? = null,
            val amount: BigDecimal? = null,
            val date: Date? = null,
        ) : Param()
    }
}
