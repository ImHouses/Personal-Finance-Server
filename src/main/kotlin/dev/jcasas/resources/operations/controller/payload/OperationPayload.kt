package dev.jcasas.resources.operations.controller.payload

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class OperationPayload(
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("amount")
    val amount: BigDecimal
)
