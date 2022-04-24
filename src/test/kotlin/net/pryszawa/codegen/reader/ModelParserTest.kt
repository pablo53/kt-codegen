package net.pryszawa.codegen.reader

import org.junit.jupiter.api.Test

class ModelParserTest {

    @Test
    fun `test Resource Reader Model Parser`() {
        val genericReader = ResourceGenericReader("api1.yml")
        val modelParser = ReaderModelParser(genericReader)
        val model = modelParser.model

    }

}