package net.pryszawa.codegen.model

data class TagObject(
    val name: String, // REQUIRED
    val description: String?,
    val externalDocs: ExternalDocumentationObject?,
)
