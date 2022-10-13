package dev.jcasas.resources.operations.controller.payload

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
import java.util.Date

data class OperationPayload(
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("amount")
    val amount: BigDecimal,
    @SerializedName("date")
    val date: Date? = null
)
