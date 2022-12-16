//package nl.topicuszorg.fhir.service.request
//
//import nl.topicuszorg.fhir.reference.ReferenceBuilder
//import nl.topicuszorg.fhir.task.TaskBuilder
//import org.hl7.fhir.r4.model.*
//import org.hl7.fhir.r4.model.ServiceRequest.ServiceRequestIntent
//import org.hl7.fhir.r4.model.ServiceRequest.ServiceRequestIntent.PROPOSAL
//import org.hl7.fhir.r4.model.ServiceRequest.ServiceRequestStatus
//import java.util.*
//import javax.security.auth.Subject
//
//class ServiceRequestBuilder {
//    private var serviceRequest = ServiceRequest()
//
//    init {
//        serviceRequest.intent = PROPOSAL
//    }
//
//    var id: String
//        get() {
//            return serviceRequest.id
//        }
//        set(id) {
//            serviceRequest.id = id
//        }
//
//    var intent: ServiceRequestIntent
//        get() {
//            return serviceRequest.intent
//        }
//        set(intent) {
//            serviceRequest.intent = intent
//        }
//
//    var status: ServiceRequestStatus
//        get() {
//            return serviceRequest.status
//        }
//        set(status) {
//            serviceRequest.status = status
//        }
//
//    var code: CodeableConcept
//        get() {
//            return serviceRequest.code
//        }
//        set(code) {
//            serviceRequest.code = code
//        }
//
//    fun buildCode(taakVerzoekType: String): ServiceRequestBuilder {
//        val coding = Coding()
//        coding.system = TAKEN_TYPE_SYSTEM
//        coding.code = taakVerzoekType
//        serviceRequest.code.coding.add(coding)
//
//        return this
//    }
//
//    fun buildRequester(referenceBuilder: ReferenceBuilder): ServiceRequestBuilder {
//        serviceRequest.requester = referenceBuilder.getResult()
//        return this
//    }
//
//    fun addPerformer(referenceBuilder: ReferenceBuilder): ServiceRequestBuilder {
//        serviceRequest.performer.add(referenceBuilder.getResult())
//        return this
//    }
//
//    fun buildSubject(referenceBuilder: ReferenceBuilder): ServiceRequestBuilder {
//        serviceRequest.subject = referenceBuilder.getResult()
//        return this
//    }
//
//
//
//    fun buildOrderDetail(taakVerzoekSubType: TaakVerzoekSubType): ServiceRequestBuilder {
//        val coding = Coding()
//        coding.system = taakVerzoekSubType.system
//        coding.code = taakVerzoekSubType.value
//        val concept = CodeableConcept()
//        concept.addCoding(coding)
//        serviceRequest.orderDetail.add(concept)
//
//        return this
//    }
//
//    fun buildOccurrenceTiming(domainTiming: nl.topicuszorg.spreekuur.taken.taakverzoek.domain.Timing): ServiceRequestBuilder {
//        if (domainTiming.duration.days != 1 || domainTiming.period != null) {
//            buildOccurrenceTimingRepeat(domainTiming)
//        } else {
//            buildOccurrenceTimingEvent(domainTiming)
//        }
//
//        return this
//    }
//
//    private fun buildOccurrenceTimingRepeat(domainTiming: nl.topicuszorg.spreekuur.taken.taakverzoek.domain.Timing) {
//        val timing = Timing()
//        timing.repeat.boundsPeriod.start = Date.from(domainTiming.startDateTime)
//        timing.event.add(DateTimeType(Date.from(domainTiming.startDateTime)))
//        domainTiming.endDateTime?.let { timing.repeat.boundsPeriod.end = Date.from(it) }
//        timing.repeat.durationUnit = Timing.UnitsOfTime.D
//        timing.repeat.duration = BigDecimal(domainTiming.duration.days)
//        timing.repeat.count = domainTiming.numberOfOccurrences
//        domainTiming.period?.let {
//            timing.repeat.periodUnit = Timing.UnitsOfTime.D
//            timing.repeat.period = BigDecimal(it.days)
//        }
//        serviceRequest.occurrence = timing
//    }
//
//    private fun buildOccurrenceTimingEvent(domainTiming: nl.topicuszorg.spreekuur.taken.taakverzoek.domain.Timing) {
//        val timing = Timing()
//        timing.event.add(DateTimeType(Date.from(domainTiming.startDateTime)))
//        serviceRequest.occurrence = timing
//    }
//
//    fun buildPatientInstruction(patientInstruction: String): ServiceRequestBuilder {
//        serviceRequest.patientInstruction = patientInstruction
//
//        return this
//    }
//
//    fun getResult() = serviceRequest
//
//}