package dev.jcasas.resources.operations.infrastructure

import dev.jcasas.resources.operations.domain.model.Operation
import dev.jcasas.resources.operations.domain.repository.OperationRepository
import dev.jcasas.resources.operations.infrastructure.mapper.toDomainModel
import dev.jcasas.resources.operations.infrastructure.mapper.toStorageModel
import dev.jcasas.resources.operations.infrastructure.model.OperationStorageModel
import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase

class MongoDbOperationsRepository(
    private val database: CoroutineDatabase
) : OperationRepository {

    private val collection: CoroutineCollection<OperationStorageModel> by lazy {
        database.getCollection(COLLECTION_NAME)
    }

    override suspend fun get(id: String): Operation? = collection.findOneById(
        ObjectId(id)
    )?.toDomainModel()

    override suspend fun getAll(): List<Operation> = collection.find().toList().map { it.toDomainModel() }

    override suspend fun add(operation: Operation): Operation {
        val storageModel = operation.toStorageModel()
        collection.insertOne(storageModel)
        return collection.findOneById(storageModel.id)?.toDomainModel() ?: throw Exception("Aggregation failed!")
    }

    override suspend fun update(newOperation: Operation): Operation {
        val storageModel = newOperation.toStorageModel()
        val id = ObjectId(newOperation.id ?: throw IllegalArgumentException())
        collection.updateOneById(id = id, storageModel, updateOnlyNotNullProperties = true)
        return collection.findOneById(id = id)?.toDomainModel() ?: throw Exception("Update operation failed!")
    }

    override suspend fun remove(id: String) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val COLLECTION_NAME = "operations"
    }
}
