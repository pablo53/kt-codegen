package net.pryszawa.codegen.reader

import net.pryszawa.codegen.model.OpenAPI3

interface ModelParser {
    val model: OpenAPI3
}
