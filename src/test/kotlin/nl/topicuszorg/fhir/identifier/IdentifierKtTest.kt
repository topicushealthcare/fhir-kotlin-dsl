package nl.topicuszorg.fhir.identifier

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*


const val TEST_SYSTEM = "test system"
const val TEST_VALUE = "test value"

internal class IdentifierKtTest {

    @Test
    fun identifier() {
        val identifier = identifier(TEST_SYSTEM, TEST_VALUE)

        assertEquals(TEST_SYSTEM, identifier.system)
        assertEquals(TEST_VALUE, identifier.value)
    }
}