package net.pryszawa.codegen.model

data class OAuthFlowObject(
    val authorizationUrl: String,
    val tokenUrl: String,
    val refreshUrl: String?,
    val scopes: Map<String, String>,
)
// CHECK: Open API specs seems to be inconsistent between which fields are mandatory and its examoles
