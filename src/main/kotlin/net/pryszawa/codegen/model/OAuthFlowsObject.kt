package net.pryszawa.codegen.model

data class OAuthFlowsObject(
    val implicit: OAuthFlowObject?,
    val password: OAuthFlowObject?,
    val clientCredentials: OAuthFlowObject?,
    val authorizationCode: OAuthFlowObject?,
)
