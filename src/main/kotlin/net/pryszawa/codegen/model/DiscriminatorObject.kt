package net.pryszawa.codegen.model

data class DiscriminatorObject(
    val propertyName: String,
    val mapping: Map<String, String>?,
)
