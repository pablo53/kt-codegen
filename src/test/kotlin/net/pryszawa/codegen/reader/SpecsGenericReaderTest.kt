package net.pryszawa.codegen.reader

import org.junit.jupiter.api.Test

class SpecsGenericReaderTest {

    @Test
    fun `test ResourceGenericReader`() {
        val genericReader = ResourceGenericReader("api1.yml")
        val genericSpecs = genericReader.specs
        // TODO
    }

}