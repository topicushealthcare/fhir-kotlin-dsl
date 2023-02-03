package nl.topicuszorg.fhir.service.request

import nl.topicuszorg.fhir.code.CodeableConceptBuilder
import nl.topicuszorg.fhir.reference.ReferenceBuilder
import nl.topicuszorg.fhir.timing.TimingBuilder
import org.hl7.fhir.r4.model.*
import org.hl7.fhir.r4.model.ServiceRequest.ServiceRequestIntent
import org.hl7.fhir.r4.model.ServiceRequest.ServiceRequestIntent.PROPOSAL
import org.hl7.fhir.r4.model.ServiceRequest.ServiceRequestStatus

/**
 * ServiceRequest represents an order or proposal or plan, as distinguished by ServiceRequest.intent to perform a
 * diagnostic or other service on or for a patient.
 *
 * **See Also:** [hl7.org documentation](http://hl7.org/fhir/R4B/servicerequest.html)
 */
fun serviceRequest(serviceRequestBuilder: ServiceRequestBuilder.() -> Unit): ServiceRequest {
    return ServiceRequestBuilder().apply(serviceRequestBuilder).getResult()
}

class ServiceRequestBuilder {
    private var serviceRequest = ServiceRequest()

    init {
        serviceRequest.intent = PROPOSAL
    }

    var id: String
        get() {
            return serviceRequest.id
        }
        set(id) {
            serviceRequest.id = id
        }

    var intent: ServiceRequestIntent
        get() {
            return serviceRequest.intent
        }
        set(intent) {
            serviceRequest.intent = intent
        }

    var status: ServiceRequestStatus
        get() {
            return serviceRequest.status
        }
        set(status) {
            serviceRequest.status = status
        }

    var patientInstruction: String
        get() {
            return serviceRequest.patientInstruction
        }
        set(patientInstruction) {
            serviceRequest.patientInstruction = patientInstruction
        }

    private fun buildRequester(referenceBuilder: ReferenceBuilder): ServiceRequestBuilder {
        serviceRequest.requester = referenceBuilder.getResult()
        return this
    }

    fun requester(referenceBuilder: ReferenceBuilder.() -> Unit) {
        val builder = ReferenceBuilder().apply(referenceBuilder)
        buildRequester(builder)
    }

    private fun addPerformer(referenceBuilder: ReferenceBuilder): ServiceRequestBuilder {
        serviceRequest.performer.add(referenceBuilder.getResult())
        return this
    }

    fun performer(referenceBuilder: ReferenceBuilder.() -> Unit) {
        val builder = ReferenceBuilder().apply(referenceBuilder)
        addPerformer(builder)
    }

    private fun buildSubject(referenceBuilder: ReferenceBuilder): ServiceRequestBuilder {
        serviceRequest.subject = referenceBuilder.getResult()
        return this
    }

    fun subject(referenceBuilder: ReferenceBuilder.() -> Unit) {
        val builder = ReferenceBuilder().apply(referenceBuilder)
        buildSubject(builder)
    }

    private fun buildOrderDetail(codeableConceptBuilder: CodeableConceptBuilder): ServiceRequestBuilder {
        serviceRequest.orderDetail.add(codeableConceptBuilder.getResult())
        return this
    }

    fun orderDetail(codeableConceptBuilder: CodeableConceptBuilder.() -> Unit) {
        val builder = CodeableConceptBuilder().apply(codeableConceptBuilder)
        buildOrderDetail(builder)
    }

    private fun buildCode(codeableConceptBuilder: CodeableConceptBuilder): ServiceRequestBuilder {
        serviceRequest.code = codeableConceptBuilder.getResult()
        return this
    }

    fun code(codeableConceptBuilder: CodeableConceptBuilder.() -> Unit) {
        val builder = CodeableConceptBuilder().apply(codeableConceptBuilder)
        buildCode(builder)
    }

    private fun buildOccurrenceTiming(timingBuilder: TimingBuilder) : ServiceRequestBuilder {
        serviceRequest.occurrence = timingBuilder.getResult()
        return this
    }

    fun occurrenceTiming(timingBuilder: TimingBuilder.() -> Unit) {
        val builder = TimingBuilder().apply(timingBuilder)
        buildOccurrenceTiming(builder)
    }

    //TODO buildOccurrenceTimingEvent
    //TODO occurrenceTimingRepeat
    fun getResult() = serviceRequest

}