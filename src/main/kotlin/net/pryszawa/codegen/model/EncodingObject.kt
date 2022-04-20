package net.pryszawa.codegen.model

data class EncodingObject(
    val contentType: String?,
    val headers: Map<String, HeaderObject>?,
    val style: Style?,
    val explode: Boolean?,
    val allowReserved: Boolean?,
)
