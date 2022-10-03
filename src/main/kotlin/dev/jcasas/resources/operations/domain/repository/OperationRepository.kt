package dev.jcasas.resources.operations.domain.repository

import dev.jcasas.resources.operations.domain.model.Operation
import java.math.BigDecimal
import java.util.Date

interface OperationRepository {

    suspend fun get(id: String): Operation

    suspend fun getAll(): List<Operation>

    suspend fun add(param: Param.Add): Operation

    suspend fun update(param: Param.Update)

    suspend fun remove(id: String)

    sealed class Param {
        data class Add(
            val name: String,
            val type: String,
            val category: String,
            val amount: BigDecimal,
            val date: Date,
        )

        data class Update(
            val id: String,
            val name: String? = null,
            val type: Operation.Type? = null,
            val category: String? = null,
            val amount: BigDecimal? = null,
            val date: Date? = null,
        )
    }
}