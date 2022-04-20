package net.pryszawa.codegen.model

data class HeaderObject(
    val `$ref`: String?,
    val description: String?,
    val required: Boolean = false,
    val deprecated: Boolean = false,
    @Deprecated("Not recommended") val allowEmptyValue: Boolean = false,
    val style: Style?,
    val explode: Boolean?,
    val allowReserved: Boolean?,
    val schema: SchemaObject?,
    val example: Map<String, Any>?,
    val examples: Map<String, ExampleObject>?,
    val content: Map<String, MediaTypeObject>?,
)
