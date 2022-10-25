package dev.jcasas.plugins

import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

object Database {
    private val connectionString = System.getenv("MONGO_CONNECTION_STRING") ?: "mongodb://localhost:27017"
    private val client by lazy { KMongo.createClient(connectionString) }
    val instance by lazy {
        with(client.coroutine) {
            getDatabase(System.getenv("DATABASE_NAME") ?: "personal_finance_dev")
        }
    }
}