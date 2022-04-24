package net.pryszawa.codegen.model

data class SecuritySchemeObject(
    val type: String, // REQUIRED
    val description: String?,
    val name: String, // REQUIRED
    val `in`: String, // REQUIRED, allowed values: query, header, cookie
    val scheme: String, // REQUIRED
    val bearerFormat: String,
    val flows: OAuthFlowsObject, // REQUIRED
    val openIdConnectUrl: String, // REQUIRED
)
