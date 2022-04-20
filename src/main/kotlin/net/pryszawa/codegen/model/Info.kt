package net.pryszawa.codegen.model

data class Info(
    val title: String,
    val description: String?,
    val termsOfService: String?,
    val contact: Contact?,
    val license: License?,
    val version: String,
)
