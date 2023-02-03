package nl.topicuszorg.fhir.service.request

import nl.topicuszorg.fhir.identifier.TEST_SYSTEM
import nl.topicuszorg.fhir.identifier.TEST_VALUE
import nl.topicuszorg.fhir.identifier.identifier
import nl.topicuszorg.fhir.reference.TEST_URL
import nl.topicuszorg.fhir.task.TEST_DESCRIPTION
import nl.topicuszorg.fhir.task.TEST_ID
import org.hl7.fhir.r4.model.ServiceRequest.ServiceRequestIntent.PLAN
import org.hl7.fhir.r4.model.ServiceRequest.ServiceRequestIntent.PROPOSAL
import org.hl7.fhir.r4.model.ServiceRequest.ServiceRequestStatus.ACTIVE
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.Instant.now

class ServiceRequestTest {
    @Test
    fun `Minimal Servicerequest`() {
        val serviceRequest = serviceRequest {

        }
        assertEquals(PROPOSAL, serviceRequest.intent)
    }

    @Test
    fun `Full Servicerequest`() {
        val eventIntant = now()
        val serviceRequest = serviceRequest {
            id = TEST_ID
            intent = PLAN
            patientInstruction = TEST_DESCRIPTION
            status = ACTIVE
            requester {
                literalReference = TEST_URL
            }
            performer {
                identifier = identifier(TEST_SYSTEM, TEST_VALUE)
            }
            subject {
                literalReference = TEST_URL
            }
            orderDetail {
                coding {
                    coding(TEST_SYSTEM, TEST_VALUE)
                    coding("some-coding", "some-value")
                }
            }
            code {
                coding {
                    coding(TEST_SYSTEM, TEST_VALUE)
                }
            }
            occurrenceTiming {
                event = listOf(eventIntant)
            }
        }

        assertEquals(TEST_ID, serviceRequest.id)
        assertEquals(PLAN, serviceRequest.intent)
        assertEquals(TEST_DESCRIPTION, serviceRequest.patientInstruction)
        assertEquals(ACTIVE, serviceRequest.status)
        assertEquals(TEST_URL, serviceRequest.requester.reference)
        assertEquals(TEST_SYSTEM, serviceRequest.performer.first().identifier.system)
        assertEquals(TEST_VALUE, serviceRequest.performer.first().identifier.value)
        assertEquals(TEST_URL, serviceRequest.subject.reference)
        assertEquals(2, serviceRequest.orderDetail.first().coding.size)
        assertEquals(TEST_SYSTEM, serviceRequest.orderDetail.first().coding.first().system)
        assertEquals(TEST_VALUE, serviceRequest.orderDetail.first().coding.first().code)
        assertEquals("some-coding", serviceRequest.orderDetail.first().coding.last().system)
        assertEquals("some-value", serviceRequest.orderDetail.first().coding.last().code)
        assertEquals(TEST_SYSTEM, serviceRequest.code.coding.first().system)
        assertEquals(TEST_VALUE, serviceRequest.code.coding.first().code)
        assertEquals(eventIntant.toEpochMilli(), serviceRequest.occurrenceTiming.event.first().value.toInstant().toEpochMilli())
    }
}