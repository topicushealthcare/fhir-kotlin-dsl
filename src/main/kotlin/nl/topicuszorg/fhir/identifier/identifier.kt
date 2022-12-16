package nl.topicuszorg.fhir.identifier

import org.hl7.fhir.r4.model.Identifier

/**
 * A numeric or alphanumeric string that is associated with a single object or entity within a given system.
 *
 * **See Also:** [hl7.org documentation](https://www.hl7.org/fhir/datatypes.html#identifier)
 */
fun identifier(system: String, value: String): Identifier {
    val identifier = Identifier()
    identifier.system = system
    identifier.value = value
    return identifier
}