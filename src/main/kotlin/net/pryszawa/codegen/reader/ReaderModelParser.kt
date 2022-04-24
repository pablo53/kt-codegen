package net.pryszawa.codegen.reader

import net.pryszawa.codegen.model.CallbackObject
import net.pryszawa.codegen.model.ComponentObjects
import net.pryszawa.codegen.model.Contact
import net.pryszawa.codegen.model.EncodingObject
import net.pryszawa.codegen.model.ExampleObject
import net.pryszawa.codegen.model.ExternalDocumentationObject
import net.pryszawa.codegen.model.Format
import net.pryszawa.codegen.model.HeaderObject
import net.pryszawa.codegen.model.InfoObject
import net.pryszawa.codegen.model.LicenseObject
import net.pryszawa.codegen.model.LinkObject
import net.pryszawa.codegen.model.MediaTypeObject
import net.pryszawa.codegen.model.OAuthFlowObject
import net.pryszawa.codegen.model.OAuthFlowsObject
import net.pryszawa.codegen.model.OpenAPI3
import net.pryszawa.codegen.model.OperationObject
import net.pryszawa.codegen.model.ParameterLocation
import net.pryszawa.codegen.model.ParameterObject
import net.pryszawa.codegen.model.PathItemObject
import net.pryszawa.codegen.model.RequestBodyObject
import net.pryszawa.codegen.model.ResponseObject
import net.pryszawa.codegen.model.SchemaObject
import net.pryszawa.codegen.model.SecurityRequirementObject
import net.pryszawa.codegen.model.SecuritySchemeObject
import net.pryszawa.codegen.model.ServerObject
import net.pryszawa.codegen.model.ServerVariableOject
import net.pryszawa.codegen.model.Style
import net.pryszawa.codegen.model.TagObject
import net.pryszawa.codegen.model.Type

class ReaderModelParser(
    private val reader: SpecsGenericReader,
) : ModelParser {

    override val model: OpenAPI3
        get() = OpenAPI3(
            openapi = reader.specs["openapi"] as String,
            info = (reader.specs["info"] as Map<String, Any>).toInfoObject(),
            servers = (reader.specs["servers"] as List<Map<String, Any>>?)?.map { serverSpecs -> serverSpecs.toServerObject() },
            paths = (reader.specs["paths"] as Map<String, Map<String, Any>>).map { it.key to it.value.toPathItemObject() }.toMap(),
            components = (reader.specs["components"] as Map<String, Any>?)?.toComponentObject(),
            security = (reader.specs["security"] as List<Map<String, Any>>?)?.map { it.toSecurity() },
            tags = (reader.specs["tags"] as List<Map<String, Any>>?)?.map { it.toTagObject() },
            externalDocs = (reader.specs["externalDocs"] as Map<String, Any>?)?.toExternalDocumentationObject(),
        )

    private fun Map<String, Any>.toInfoObject(): InfoObject =
        InfoObject(
            title = this["title"] as String,
            description = this["description"] as String?,
            termsOfService = this["termsOfService"] as String?,
            contact = (this["contact"] as Map<String, Any>?)?.toContactObject(),
            license =  (this["license"] as Map<String, Any>?)?.toLicenseObject(),
            version = this["version"] as String,
        )

    private fun Map<String, Any>.toContactObject(): Contact =
        Contact(
            name = this["name"] as String?,
            url = this["url"] as String?,
            email = this["email"] as String?,
        )

    private fun Map<String, Any>.toLicenseObject(): LicenseObject =
        LicenseObject(
            name = this["name"] as String,
            url = this["url"] as String?,
        )

    private fun Map<String, Any>.toServerVariableOject(): ServerVariableOject =
        ServerVariableOject(
            enum = this["enum"] as List<String>?,
            default = this["default"] as String,
            description = this["description"] as String?,
        )

    private fun Map<String, Any>.toServerObject() =
        ServerObject(
            url = this["url"] as String,
            description = this["description"] as String?,
            variables = (this["variables"] as Map<String, Map<String, Any>>?)?.map { it.key to it.value.toServerVariableOject() }?.toMap(),
        )

    private fun Map<String, Any>.toOperationObject(): OperationObject =
        OperationObject(
            tags = this["tags"] as List<String>?,
            summary = this["summary"] as String?,
            description = this["description"] as String?,
            externalDocs = (this["externalDocs"] as Map<String, Any>?)?.toExternalDocumentationObject(),
            operationId = this["operationId"] as String?,
            parameters = (this["parameters"] as List<Map<String, Any>>).map { it.toParameterObject() },
            requestBody = (this["requestBody"] as Map<String, Any>?)?.let { it.toRequestBodyObject() },
            responses = (this["responses"] as Map<String, Map<String, Any>>).map { it.key to it.value.toResponseObject() }.toMap(),
            callbacks = (this["callbacks"] as Map<String, Map<String, Any>>?)?.map { it.key to it.value.toCallbackObject() }?.toMap(),
            deprecated = (this["deprecated"] as Boolean?) ?: false,
            security = (this["security"] as List<Map<String, Any>>?)?.map { it.toSecurity() },
            servers = (this["servers"] as List<Map<String, Any>>?)?.map { it.toServerObject() },
        )

    private fun Map<String, Any>.toRequestBodyObject(): RequestBodyObject =
        RequestBodyObject(
            `$ref` = this["\$ref"] as String?,
            description = this["description"] as String?,
            content = (this["content"] as Map<String, Any>?)?.map { it.key to (it.value as Map<String, Any>).toMediaTypeObject() }?.toMap(),
            required = (this["required"] as Boolean?) ?: false,
        )

    private fun Map<String, Any>.toMediaTypeObject(): MediaTypeObject =
        MediaTypeObject(
            schema = (this["schema"] as Map<String, Any>?)?.toSchemaObject(),
            example = this["example"] as Map<String, Any>?,
            examples = (this["examples"] as List<Map<String, Any>>?)?.map { it.toExampleObject() },
            encoding = (this["encoding"] as Map<String, Map<String, Any>>?)?.map { it.key to it.value.toEncodingObject() }?.toMap(),
        )

    private fun Map<String, Any>.toEncodingObject(): EncodingObject =
        EncodingObject(
            contentType = this["contentType"] as String?,
            headers = (this["headers"] as Map<String, Map<String, Any>>?)?.map { it.key to it.value.toHeaderObject() }?.toMap(),
            style = (this["style"] as String?)?.let { Style.valueOf(it) },
            explode = (this["explode"] as Boolean?) ?: false,
            allowReserved = (this["allowReserved"] as Boolean?) ?: false,
        )

    private fun Map<String, Any>.toHeaderObject(): HeaderObject =
        HeaderObject(
            `$ref` = this["\$ref"] as String?,
            description = this["description"] as String?,
            required = (this["required"] as Boolean?) ?: false,
            deprecated = (this["deprecated"] as Boolean?) ?: false,
            allowEmptyValue = (this["allowEmptyValue"] as Boolean?) ?: false,
            style = (this["style"] as String?)?.let { Style.valueOf(it) },
            explode = (this["explode"] as Boolean?) ?: false,
            allowReserved = (this["allowReserved"] as Boolean?) ?: false,
            schema = (this["schema"] as Map<String, Any>?)?.toSchemaObject(),
            example = this["example"] as Map<String, Any>?,
            examples = (this["examples"] as Map<String, Map<String, Any>>?)?.map { it.key to it.value.toExampleObject() }?.toMap(),
            content = (this["content"] as Map<String, Map<String, Any>>?)?.map { it.key to it.value.toMediaTypeObject() }?.toMap()
        )

    private fun Map<String, Any>.toExampleObject(): ExampleObject =
        ExampleObject(
            `$ref` = this["\$ref"] as String?,
            summary = this["summary"] as String?,
            description = this["description"] as String?,
            value = this["value"] as Map<String, Any>?,
            externalValue = this["externalValue"] as String?,
        )

    private fun Map<String, Any>.toSchemaObject(): SchemaObject =
        SchemaObject(
            `$ref` = this["\$ref"] as String?,
            type = (this["type"] as String?)?.let { Type.valueOf(it) },
            allOf = (this["allOf"] as List<Map<String, Any>>?)?.map { it.toSchemaObject() },
            oneOf = (this["oneOf"] as List<Map<String, Any>>?)?.map { it.toSchemaObject() },
            anyOf = (this["anyOf"] as List<Map<String, Any>>?)?.map { it.toSchemaObject() },
            not = (this["not"] as List<Map<String, Any>>?)?.map { it.toSchemaObject() },
            items = (this["items"] as Map<String, Any>?)?.toSchemaObject(),
            properties = (this["properties"] as Map<String, Any>?)?.map { it.key to (it.value as Map<String, Any>).toSchemaObject() }?.toMap(),
            additionalProperties = (this["additionalProperties"] as Map<String, Any>?)?.map { it.key to (it.value as Map<String, Any>).toSchemaObject() }?.toMap(),
            description = this["description"] as String?,
            format = (this["format"] as String?)?.let { Format.valueOf(it) },
            default = this["default"] as String?,
            title = this["title"] as String?,
            multipleOf = this["multipleOf"] as Int?,
            maximum = this["maximum"] as Int?,
            exclusiveMaximum = this["exclusiveMaximum"] as Int?,
            minimum = this["minimum"] as Int?,
            exclusiveMinimum = this["exclusiveMinimum"] as Int?,
            maxLength = this["maxLength"] as Int?,
            minLength = this["minLength"] as Int?,
            pattern = this["pattern"] as String?,
            maxItems = this["maxItems"] as Int?,
            minItems = this["minItems"] as Int?,
            uniqueItems = this["uniqueItems"] as Int?,
            maxProperties = this["maxProperties"] as Int?,
            minProperties = this["minProperties"] as Int?,
            required = (this["required"] as Boolean?) ?: false,
            enum = this["enum"] as List<String>?,
    )

    private fun Map<String, Any>.toPathItemObject(): PathItemObject =
        PathItemObject(
            `$ref` = this["\$ref"] as String?,
            summary = this["summary"] as String?,
            description = this["description"] as String?,
            get = (this["get"] as Map<String, Any>?)?.toOperationObject(),
            put = (this["put"] as Map<String, Any>?)?.toOperationObject(),
            post = (this["post"] as Map<String, Any>?)?.toOperationObject(),
            delete = (this["delete"] as Map<String, Any>?)?.toOperationObject(),
            options = (this["options"] as Map<String, Any>?)?.toOperationObject(),
            head = (this["head"] as Map<String, Any>?)?.toOperationObject(),
            patch = (this["patch"] as Map<String, Any>?)?.toOperationObject(),
            trace = (this["trace"] as Map<String, Any>?)?.toOperationObject(),
            servers = (this["servers"] as List<Map<String, Any>>?)?.map { it.toServerObject() },
            parameters = (this["parameters"] as List<Map<String, Any>>?)?.map { it.toParameterObject() },
        )

    private fun Map<String, Any>.toParameterObject(): ParameterObject =
        ParameterObject(
            `$ref` = this["\$ref"] as String?,
            name = this["name"] as String?,
            `in` = (this["in"] as String?)?.let { ParameterLocation.valueOf(it) },
            description = this["description"] as String?,
            required = (this["required"] as Boolean?) ?: false,
            deprecated = (this["deprecated"] as Boolean?) ?: false,
            allowEmptyValue = (this["allowEmptyValue"] as Boolean?) ?: false,
            style = (this["style"] as String?)?.let { Style.valueOf(it) },
            explode = (this["explode"] as Boolean?) ?: false,
            allowReserved = (this["allowReserved"] as Boolean?) ?: false,
            schema = (this["schema"] as Map<String, Any>?)?.toSchemaObject(),
            example = this["example"] as Map<String, Any>?,
            examples = (this["examples"] as Map<String, Map<String, Any>>?)?.map { it.key to it.value.toExampleObject() }?.toMap(),
            content = (this["content"] as Map<String, Map<String, Any>>?)?.map { it.key to it.value.toMediaTypeObject() }?.toMap(),
        )

    private fun Map<String, Any>.toExternalDocumentationObject(): ExternalDocumentationObject =
        ExternalDocumentationObject(
            description = this["description"] as String?,
            url = this["url"] as String,
        )

    private fun Map<String, Any>.toResponseObject(): ResponseObject =
        ResponseObject(
            `$ref` = this["\$ref"] as String?,
            description = this["description"] as String?,
            headers = (this["headers"] as Map<String, Map<String, Any>>?)?.map { it.key to it.value.toHeaderObject() }?.toMap(),
            content = (this["content"] as Map<String, Any>?)?.map { it.key to (it.value as Map<String, Any>).toMediaTypeObject() }?.toMap(),
            links = (this["links"] as Map<String, Map<String, Any>>?)?.map { it.key to it.value.toLinkObject() }?.toMap(),
        )

    private fun Map<String, Any>.toLinkObject(): LinkObject =
        LinkObject(
            `$ref` = this["\$ref"] as String?,
            operationRef = this["operationRef"] as String?,
            operationId = this["operationId"] as String?,
            parameters = this["parameters"] as Map<String, String>?, // FIXME: Any | {expression}
            requestBody = this["requestBody"] as String?, // FIXME: Any | {expression}
            description = this["description"] as String?,
            server = (this["server"] as Map<String, Any>?)?.toServerObject(),
        )

    private fun Map<String, Any>.toComponentObject(): ComponentObjects =
        ComponentObjects(
            schemas = (this["schemas"] as Map<String, Map<String, Any>>?)?.map { it.key to it.value.toSchemaObject() }?.toMap(),
            responses = (this["schemas"] as Map<String, Map<String, Any>>?)?.map { it.key to it.value.toResponseObject() }?.toMap(),
            parameters = (this["parameters"] as Map<String, Map<String, Any>>?)?.map { it.key to it.value.toParameterObject() }?.toMap(),
            examples = (this["examples"] as Map<String, Map<String, Any>>?)?.map { it.key to it.value.toExampleObject() }?.toMap(),
            requestBodies = (this["requestBodies"] as Map<String, Map<String, Any>>?)?.map { it.key to it.value.toRequestBodyObject() }?.toMap(),
            headers = (this["headers"] as Map<String, Map<String, Any>>?)?.map { it.key to it.value.toHeaderObject() }?.toMap(),
            securitySchemes = (this["securitySchemes"] as Map<String, Map<String, Any>>?)?.map { it.key to it.value.toSecuritySchemeObject() }?.toMap(),
            links = (this["links"] as Map<String, Map<String, Any>>?)?.map { it.key to it.value.toLinkObject() }?.toMap(),
            callbacks = (this["callbacks"] as Map<String, Map<String, Any>>?)?.map { it.key to it.value.toCallbackObject() }?.toMap(),
        )

    private fun Map<String, Any>.toSecuritySchemeObject(): SecuritySchemeObject =
        SecuritySchemeObject(
            type = this["type"] as String,
            description = this["description"] as String?,
            name = this["name"] as String,
            `in` = this["in"] as String,
            scheme = this["scheme"] as String,
            bearerFormat = this["bearerFormat"] as String,
            flows = (this["flows"] as Map<String, Any>).toOAuthFlowsObject(),
            openIdConnectUrl = this["openIdConnectUrl"] as String,
        )

    private fun Map<String, Any>.toOAuthFlowsObject(): OAuthFlowsObject =
        OAuthFlowsObject(
            implicit = (this["implicit"] as Map<String, Any>?)?.toOAuthFlowObject(),
            password = (this["password"] as Map<String, Any>?)?.toOAuthFlowObject(),
            clientCredentials = (this["clientCredentials"] as Map<String, Any>?)?.toOAuthFlowObject(),
            authorizationCode = (this["authorizationCode"] as Map<String, Any>?)?.toOAuthFlowObject(),
        )

    private fun Map<String, Any>.toOAuthFlowObject(): OAuthFlowObject =
        OAuthFlowObject(
            authorizationUrl = this["authorizationUrl"] as String,
            tokenUrl = this["tokenUrl"] as String,
            refreshUrl = this["refreshUrl"] as String?,
            scopes = this["scopes"] as Map<String, String>,
        )

    private fun Map<String, Any>.toTagObject(): TagObject =
        TagObject(
            name = this["name"] as String,
            description = this["description"] as String?,
            externalDocs = (this["externalDocs"] as Map<String, Any>?)?.toExternalDocumentationObject(),
        )

    private fun Map<String, Any>.toSecurity(): SecurityRequirementObject = this as Map<String, List<String>>

    private fun Map<String, Any>.toCallbackObject(): CallbackObject =
        this.map { it.key to (it.value as Map<String, Any>).toPathItemObject() }.toMap() // TODO: handle $ref

}
