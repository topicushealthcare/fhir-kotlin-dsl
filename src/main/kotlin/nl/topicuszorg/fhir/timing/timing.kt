package nl.topicuszorg.fhir.timing

import org.hl7.fhir.r4.model.DateTimeType
import org.hl7.fhir.r4.model.Timing
import java.time.Instant
import java.util.*

class TimingBuilder {
    private val timing = Timing()

    var event: List<Instant>
        get() {
            return timing.event.map { it.value.toInstant()  }
        }
        set(event) {
           timing.event = event.map {  DateTimeType(Date.from(it )) }
        }

    //TODO other timing properties

    fun getResult() = timing
}