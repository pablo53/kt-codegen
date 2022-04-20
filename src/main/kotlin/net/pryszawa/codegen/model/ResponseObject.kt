package net.pryszawa.codegen.model

data class ResponseObject(
    val `$ref`: String?,
    val description: String?, // REQUIRED if not $ref
    val headers: Map<String, HeaderObject>?,
    val content: Map<String, MediaTypeObject>?,
    val links: Map<String, LinkObject>?,
)
