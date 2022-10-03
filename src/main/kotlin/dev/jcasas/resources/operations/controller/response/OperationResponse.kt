package dev.jcasas.resources.operations.controller.response

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
import java.util.Date

data class OperationResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("amount")
    val amount: BigDecimal,
    @SerializedName("date")
    val date: Date,
)
