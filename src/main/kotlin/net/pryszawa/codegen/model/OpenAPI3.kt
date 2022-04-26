package net.pryszawa.codegen.model

data class OpenAPI3(
    val openapi: String,
    val info: InfoObject,
    val jsonSchemaDialect: String?,
    val servers: List<ServerObject>?,
    val paths: Map<String, PathItemObject>,
    val webhooks: Map<String, PathItemObject>,
    val components: ComponentObjects?,
    val security: List<SecurityRequirementObject>?,
    val tags: List<TagObject>?,
    val externalDocs: ExternalDocumentationObject?,
)
