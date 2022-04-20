package net.pryszawa.codegen.reader

import java.io.File

class FileSpecsGenericReader(
    private val file: File,
) : SpecsGenericReader {

    override val specs: Map<String, Any>
        get() = InputStreamSpecsGenericReader(file.inputStream()).specs

}
