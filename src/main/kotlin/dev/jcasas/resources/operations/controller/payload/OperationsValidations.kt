package dev.jcasas.resources.operations.controller.payload

import io.ktor.server.plugins.requestvalidation.RequestValidationConfig
import io.ktor.server.plugins.requestvalidation.ValidationResult

fun RequestValidationConfig.configureOperationsValidations() {
    validate<OperationUpdatePayload> { payload ->
        when {
            payload.id.isEmpty() -> ValidationResult.Invalid("Invalid Id")
            payload.name != null && payload.name.isEmpty() -> ValidationResult.Invalid("Name must not be empty.")
            payload.category != null && payload.category.isEmpty() ->
                ValidationResult.Invalid("Category must not be empty.")
            payload.type != null && payload.type !in setOf("INCOME", "EXPENSE") ->
                ValidationResult.Invalid("Invalid operation type.")
            else -> ValidationResult.Valid
        }
    }

    validate<OperationPayload> { payload ->
        with(payload) {
            when {
                name.isEmpty() -> ValidationResult.Invalid("Name must not be empty.")
                category.isEmpty() -> ValidationResult.Invalid("Category must not be empty.")
                type !in setOf("INCOME", "EXPENSE") -> ValidationResult.Invalid("Invalid Type.")
                else -> ValidationResult.Valid
            }
        }
    }
}
