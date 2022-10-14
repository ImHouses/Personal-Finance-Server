package dev.jcasas.plugins

import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

object Database {
    // TODO: Get Database connection information from configuration file.
    val instance = with(KMongo.createClient().coroutine) {
        getDatabase("personal_finances")
    }
}