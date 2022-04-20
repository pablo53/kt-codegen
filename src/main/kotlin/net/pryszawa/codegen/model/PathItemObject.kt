package net.pryszawa.codegen.model

data class PathItemObject(
    val `$ref`: String?,
    val summary: String?,
    val description: String?,
    val get: OperationObject?,
    val put: OperationObject?,
    val post: OperationObject?,
    val delete: OperationObject?,
    val options: OperationObject?,
    val head: OperationObject?,
    val patch: OperationObject?,
    val trace: OperationObject?,
    val servers: List<ServerObject>?,
    val parameters: List<ParameterObject>
)