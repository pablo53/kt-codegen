package net.pryszawa.codegen.reader

class ResourceGenericReader(
    val classpathResource: String
) : SpecsGenericReader {

    override val specs: Map<String, Any>
        get() = InputStreamSpecsGenericReader(this::class.java.getResourceAsStream(classpathResource)).specs

}
