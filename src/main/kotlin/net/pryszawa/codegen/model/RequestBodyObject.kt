package net.pryszawa.codegen.model

data class RequestBodyObject(
    val `$ref`: String?,
    val description: String?,
    val content: Map<String, MediaTypeObject>?, // REQUIRED if not $ref
    val required: Boolean = false,
)
