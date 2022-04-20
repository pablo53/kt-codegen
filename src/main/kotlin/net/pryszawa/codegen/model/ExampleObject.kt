package net.pryszawa.codegen.model

data class ExampleObject(
    val `$ref`: String?,
    val summary: String?,
    val description: String?,
    val value: Map<String, Any>?, // REQUIRED, if externalValue not provided
    val externalValue: String?, // URL; REQUIRED, if value not provided
)
