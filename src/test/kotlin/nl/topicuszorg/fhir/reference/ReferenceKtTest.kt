package nl.topicuszorg.fhir.reference

import nl.topicuszorg.fhir.identifier.TEST_SYSTEM
import nl.topicuszorg.fhir.identifier.TEST_VALUE
import nl.topicuszorg.fhir.identifier.identifier
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

const val TEST_URL =  "https://example.org/test/reference"

internal class ReferenceKtTest {

    @Test
    fun identifierReference() {
        val identifierReference  = reference {
            identifier = identifier(TEST_SYSTEM, TEST_VALUE)
        }
        assertEquals(TEST_SYSTEM, identifierReference.identifier.system)
        assertEquals(TEST_VALUE, identifierReference.identifier.value)
    }

    @Test
    fun literalReference() {
        val identifierReference = reference {
            literalReference = TEST_URL
        }
        assertEquals(TEST_URL, identifierReference.reference)
    }
}