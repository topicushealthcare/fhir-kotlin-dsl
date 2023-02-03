package nl.topicuszorg.fhir.reference

import org.hl7.fhir.r4.model.Identifier
import org.hl7.fhir.r4.model.Reference

/**
 * Resource Reference.
 *
 * **See Also:** [hl7.org documentation](http://hl7.org/fhir/R4B/references.html#Reference)
 */
fun reference(referenceBuilder: ReferenceBuilder.() -> Unit): Reference
{
    return ReferenceBuilder().apply(referenceBuilder).getResult()
}

class ReferenceBuilder {
    private val reference = Reference()

    var identifier: Identifier
        get() {
            return   reference.identifier
        }
        set(identifier) {
            reference.identifier = identifier
        }

    var literalReference: String
        get() {
            return   reference.reference
        }
        set(referenceUrl) {
            reference.reference = referenceUrl
        }

    fun getResult() = reference
}


