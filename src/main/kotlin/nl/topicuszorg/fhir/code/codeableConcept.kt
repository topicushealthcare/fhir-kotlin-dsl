package nl.topicuszorg.fhir.code

import org.hl7.fhir.r4.model.CodeableConcept
import org.hl7.fhir.r4.model.Coding

fun codeableConcept(codeableConceptBuilder: CodeableConceptBuilder.() -> Unit): CodeableConcept
{
    return CodeableConceptBuilder().apply(codeableConceptBuilder).getResult()
}

class Codings : ArrayList<Coding>() {
    fun coding(system: String, code: String) {
        val coding = Coding()
        coding.system = system
        coding.code = code
        add(coding)
    }
}

class CodeableConceptBuilder {
    private val codeableConcept = CodeableConcept()

    fun coding(block : Codings.() -> Unit) {
        codeableConcept.coding.addAll(Codings().apply(block))
    }

    fun getResult() = codeableConcept
}