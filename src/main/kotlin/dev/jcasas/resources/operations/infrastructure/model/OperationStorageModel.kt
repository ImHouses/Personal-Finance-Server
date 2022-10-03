package dev.jcasas.resources.operations.infrastructure.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id
import org.litote.kmongo.newId
import java.math.BigDecimal
import java.util.Date

@Serializable
data class OperationStorageModel(
    @BsonId
    val id: Id<OperationStorageModel> = newId(),
    val name: String,
    val type: String,
    val category: String,
    @Contextual
    val amount: BigDecimal,
    @Contextual
    val date: Date
)
