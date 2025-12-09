package uk.gov.android.ui.componentsv2

/**
 * Functional interface for providing a standard method of rendering UI content within tests.
 */
fun interface Renderer<T : Any> {
    fun render(input: T)
}
