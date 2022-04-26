package net.pryszawa.codegen.model

data class XMLObject(
    val name: String?,
    val namespace: String?,
    val prefix: String?,
    val attribute: Boolean = false,
    val wrapped: Boolean = false,
)
