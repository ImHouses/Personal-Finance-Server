package dev.jcasas.resources.operations.infrastructure

import dev.jcasas.resources.operations.domain.model.Operation
import dev.jcasas.resources.operations.domain.repository.OperationRepository
import dev.jcasas.resources.operations.infrastructure.mapper.toDomainModel
import dev.jcasas.resources.operations.infrastructure.mapper.toStorageModel
import dev.jcasas.resources.operations.infrastructure.model.OperationStorageModel
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase

class MongoDbOperationsRepository(
    private val database: CoroutineDatabase
) : OperationRepository {

    private val collection: CoroutineCollection<OperationStorageModel> by lazy {
        database.getCollection(COLLECTION_NAME)
    }

    override suspend fun get(id: String): Operation {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): List<Operation> = collection.find().toList().map { it.toDomainModel() }

    override suspend fun add(param: OperationRepository.Param.Add): Operation {
        val storageModel = param.toStorageModel()
        collection.insertOne(storageModel)
        return collection.findOneById(storageModel.id)?.toDomainModel() ?: throw Exception("Aggregation failed!")
    }

    override suspend fun update(param: OperationRepository.Param.Update) {
        TODO("Not yet implemented")
    }

    override suspend fun remove(id: String) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val COLLECTION_NAME = "operations"
    }
}