package nl.topicuszorg.fhir.task

import nl.topicuszorg.fhir.reference.ReferenceBuilder
import org.hl7.fhir.r4.model.Task
import org.hl7.fhir.r4.model.Task.TaskIntent
import org.hl7.fhir.r4.model.Task.TaskIntent.PROPOSAL
import org.hl7.fhir.r4.model.Task.TaskStatus

/**
 * A task resource describes an activity that can be performed and tracks the state of completion of that activity.
 *
 * **See Also:** [hl7.org documentation](http://hl7.org/fhir/R4B/task.html)
 */
fun task(taskBuilder: TaskBuilder.() -> Unit): Task {
    return TaskBuilder().apply(taskBuilder).getResult()
}

class TaskBuilder {

    private val task = Task()

    init {
        task.intent = PROPOSAL
    }

    var id: String
        get() {
            return task.id
        }
        set(id) {
            task.id = id
        }

    var intent: TaskIntent
        get() {
            return task.intent
        }
        set(intent) {
            task.intent = intent
        }

    var description: String
        get() {
            return task.description
        }
        set(description) {
            task.description = description
        }

    var status: TaskStatus
        get() {
            return task.status
        }
        set(status) {
            task.status = status
        }

    private fun buildOwner(referenceBuilder: ReferenceBuilder): TaskBuilder {
        task.owner = referenceBuilder.getResult()
        return this
    }

    fun owner(referenceBuilder: ReferenceBuilder.() -> Unit) {
        val builder = ReferenceBuilder().apply(referenceBuilder)
        buildOwner(builder)
    }

    private fun buildRequester(referenceBuilder: ReferenceBuilder): TaskBuilder {
        task.requester = referenceBuilder.getResult()
        return this
    }

    fun requester(referenceBuilder: ReferenceBuilder.() -> Unit) {
        val builder = ReferenceBuilder().apply(referenceBuilder)
        buildRequester(builder)
    }

    private fun addBasedOn(referenceBuilder: ReferenceBuilder): TaskBuilder {
        task.basedOn.add(referenceBuilder.getResult())
        return this
    }

    fun basedOn(referenceBuilder: ReferenceBuilder.() -> Unit) {
        val builder = ReferenceBuilder().apply(referenceBuilder)
        addBasedOn(builder)
    }

    fun getResult(): Task = task
}





