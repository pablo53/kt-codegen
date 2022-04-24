package net.pryszawa.codegen.model

data class InfoObject(
    val title: String,
    val description: String?,
    val termsOfService: String?,
    val contact: Contact?,
    val license: LicenseObject?,
    val version: String,
)
