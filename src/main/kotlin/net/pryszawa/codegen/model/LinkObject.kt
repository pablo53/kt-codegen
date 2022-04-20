package net.pryszawa.codegen.model

data class LinkObject(
    val `$ref`: String?,
    val operationRef: String?, // mutually exclusive with operationId
    val operationId: String?, // mutually exclusive with operationRef
    val parameters: Map<String, String>?, // FIXME: Any | {exprssion}
    val requestBody: String?, // FIXME: Any | {exprssion}
    val description: String?,
    val server: ServerObject?,
)
