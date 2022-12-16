package nl.topicuszorg.fhir.task

import nl.topicuszorg.fhir.identifier.TEST_SYSTEM
import nl.topicuszorg.fhir.identifier.TEST_VALUE
import nl.topicuszorg.fhir.identifier.identifier
import nl.topicuszorg.fhir.reference.TEST_URL
import org.hl7.fhir.r4.model.Task.TaskIntent.PLAN
import org.hl7.fhir.r4.model.Task.TaskIntent.PROPOSAL
import org.hl7.fhir.r4.model.Task.TaskStatus.COMPLETED
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

const val TEST_DESCRIPTION =  "Nice stuff"
const val TEST_ID =  "42"

internal class TaskKtTest {

    @Test
    fun `minimal task`() {
        val task = task {

        }
        assertEquals(PROPOSAL, task.intent)
    }

    @Test
    fun `full task`() {
        val task = task {
            intent = PLAN
            id = TEST_ID
            description = TEST_DESCRIPTION
            status = COMPLETED
            owner {
                literalReference = TEST_URL
            }
            basedOn {
                identifier = identifier(TEST_SYSTEM, TEST_VALUE)
            }
            requester {
                literalReference= TEST_URL
            }
        }

        assertEquals(TEST_ID, task.id)
        assertEquals(PLAN, task.intent)
        assertEquals(TEST_DESCRIPTION, task.description)
        assertEquals(COMPLETED, task.status)
        assertEquals(TEST_URL, task.owner.reference)
        assertEquals(TEST_SYSTEM, task.basedOn.first().identifier.system)
        assertEquals(TEST_VALUE, task.basedOn.first().identifier.value)
        assertEquals(TEST_URL, task.requester.reference)
    }
}