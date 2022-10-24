package dev.jcasas.plugins

import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

object Database {
    private val client by lazy { KMongo.createClient(System.getenv("MONGO_CONNECTION_STRING")) }
    val instance by lazy {
        with(client.coroutine) {
            getDatabase(System.getenv("DATABASE_NAME"))
        }
    }
}