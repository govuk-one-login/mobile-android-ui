package uk.gov.android.ui.patterns.testutils

// Uppercase function names are intentional to provide a readable BDD-style test DSL
@Suppress("FunctionName")
object BDD {
    fun Given(step: String, block: () -> Unit = {}) {
        println("Step: Given $step")
        block()
    }

    fun When(step: String, block: () -> Unit = {}) {
        println("Step: When $step")
        block()
    }

    fun Then(step: String, block: () -> Unit = {}) {
        println("Step: Then $step")
        block()
    }

    fun And(step: String, block: () -> Unit = {}) {
        println("Step: And $step")
        block()
    }
}
