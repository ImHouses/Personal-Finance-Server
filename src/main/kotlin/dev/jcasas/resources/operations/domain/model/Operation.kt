package dev.jcasas.resources.operations.domain.model

import java.math.BigDecimal
import java.util.Date

data class Operation(
    val id: String? = null,
    val name: String,
    val type: Type,
    val category: String,
    val amount: BigDecimal,
    val date: Date
) {

    enum class Type {
        EXPENSE,
        INCOME
    }
}
