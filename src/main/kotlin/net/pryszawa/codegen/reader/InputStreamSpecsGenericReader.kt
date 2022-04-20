package net.pryszawa.codegen.reader

import org.yaml.snakeyaml.Yaml
import java.io.InputStream

class InputStreamSpecsGenericReader(
    private val inputStream: InputStream,
) : SpecsGenericReader {
    override val specs: Map<String, Any>
        get() = Yaml().load(inputStream)
}