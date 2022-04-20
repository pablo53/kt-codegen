package net.pryszawa.codegen.model

data class OperationObject(
    val tags: List<String>?,
    val summary: String?,
    val description: String?,
    val externalDocs: ExternalDocumentationObject?,
    val operationId: String?,
    val parameters: List<ParameterObject>?,
    val requestBody: RequestBodyObject?,
    val responses: Map<String, ResponseObject>,
    val callbacks: Map<String, CallbackObject>?, // FIXME: allow $ref
    val deprecated: Boolean = false,
    val security: List<SecurityRequirementObject>?,
    val servers: List<ServerObject>?,
)
