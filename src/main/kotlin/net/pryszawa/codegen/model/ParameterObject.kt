package net.pryszawa.codegen.model

data class ParameterObject(
    val `$ref`: String?, // REQUIRED if all the other ones are not provided
    val name: String?, // REQUIRED, if $ref not provided
    val `in`: String?, // REQUIRED, if $ref not provided
    val description: String?,
    val required: Boolean = false, // MUST equal to true, if in="path"
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
