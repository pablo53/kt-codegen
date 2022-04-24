package net.pryszawa.codegen.model

data class OpenAPI3(
    val openapi: String,
    val info: InfoObject,
    val servers: List<ServerObject>?,
    val paths: Map<String, PathItemObject>,
    val components: ComponentObjects?,
    val security: List<SecurityRequirementObject>?,
    val tags: List<TagObject>?,
    val externalDocs: ExternalDocumentationObject?,
)
