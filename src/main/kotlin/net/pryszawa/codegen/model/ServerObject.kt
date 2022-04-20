package net.pryszawa.codegen.model

data class ServerObject(
    val url: String,
    val description: String?,
    val variables: Map<String, ServerVariableOject>?,
)
