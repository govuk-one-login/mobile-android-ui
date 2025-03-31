package uk.gov.android.ui.patterns.utils

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
