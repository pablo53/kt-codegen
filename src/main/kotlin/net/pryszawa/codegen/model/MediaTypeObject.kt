package net.pryszawa.codegen.model

data class MediaTypeObject(
    val schema: SchemaObject?,
    val example: Map<String, Any>?,
    val examples: List<ExampleObject>?,
    val encoding: Map<String, EncodingObject>?
)
