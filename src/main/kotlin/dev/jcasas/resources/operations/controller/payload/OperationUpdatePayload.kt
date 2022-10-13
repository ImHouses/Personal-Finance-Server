package dev.jcasas.resources.operations.controller.payload

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
import java.util.Date

data class OperationUpdatePayload(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("category")
    val category: String? = null,
    @SerializedName("amount")
    val amount: BigDecimal? = null,
    @SerializedName("date")
    val date: Date? = null,
)