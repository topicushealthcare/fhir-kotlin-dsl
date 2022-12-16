package nl.topicuszorg.fhir.code

import nl.topicuszorg.fhir.identifier.TEST_SYSTEM
import nl.topicuszorg.fhir.identifier.TEST_VALUE
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CodeableConceptKtTest {

    @Test
    fun codeableConcept() {
        val codeableConcept = codeableConcept {
            coding {
                coding(TEST_SYSTEM, TEST_VALUE)
                coding("some-coding", "some-value")
            }
        }

        assertEquals(2, codeableConcept.coding.size)
        assertEquals(TEST_SYSTEM, codeableConcept.coding.first().system)
        assertEquals(TEST_VALUE, codeableConcept.coding.first().code)
        assertEquals("some-coding", codeableConcept.coding.last().system)
        assertEquals("some-value", codeableConcept.coding.last().code)
    }
}