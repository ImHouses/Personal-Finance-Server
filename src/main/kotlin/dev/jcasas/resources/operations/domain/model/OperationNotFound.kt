package dev.jcasas.resources.operations.domain.model

class OperationNotFound(val id: String) : Throwable("Could not find Operation with id=$id")
