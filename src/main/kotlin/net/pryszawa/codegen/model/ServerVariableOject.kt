package net.pryszawa.codegen.model

data class ServerVariableOject(
    val enum: List<String>?,
    val default: String,
    val description: String?,
)
