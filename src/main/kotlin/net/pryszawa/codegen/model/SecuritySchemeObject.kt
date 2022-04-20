package net.pryszawa.codegen.model

data class SecuritySchemeObject(
    val type: String, // EQUIRED
    val description: String?,
    val name: String, // EQUIRED
    val `in`: String, // REQUIRED, allowed values: query, header, cookie
    val scheme: String, // EQUIRED
    val bearerFormat: String,
    val flows: OAuthFlowsObject, // REQUIRED
    val openIdConnectUrl: String, // REQUIRED
)
