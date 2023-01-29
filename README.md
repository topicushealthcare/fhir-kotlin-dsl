# fhir-kotlin-dsl
Topicus Healthcare Kotlin DSL for FHIR resource construction

![Build status](https://github.com/topicushealthcare/fhir-kotlin-dsl/actions/workflows/build.yml/badge.svg)
[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=topicushealthcare_fhir-kotlin-dsl)](https://sonarcloud.io/summary/new_code?id=topicushealthcare_fhir-kotlin-dsl)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=topicushealthcare_fhir-kotlin-dsl&metric=coverage)](https://sonarcloud.io/summary/new_code?id=topicushealthcare_fhir-kotlin-dsl)

## Supported resources
### FHIR v4.3.0: R4B
- [Task](http://hl7.org/fhir/R4B/task.html)
- [ServiceRequest](http://hl7.org/fhir/R4B/servicerequest.html)

### Usage example
```
val task = task {
    intent = PLAN
    id = TEST_ID
    description = "Nice stuff"
    status = COMPLETED
    owner {
        literalReference = "https://example.org/test/owner/reference"
    }
    basedOn {
        identifier = identifier("https://example.org/test/uuid", "08733627-8a73-4408-add5-912e61030fbd")
    }
    requester {
        literalReference= "https://example.org/test/requester/reference"
    }
}
```