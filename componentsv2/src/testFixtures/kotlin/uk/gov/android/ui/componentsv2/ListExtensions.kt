package uk.gov.android.ui.componentsv2

import com.android.resources.NightMode

object ListExtensions {
    fun <T : Any> Iterable<T>.toNightModePairs(): List<Pair<T, NightMode>> = cartesianProduct(
        listOf(
            NightMode.NOTNIGHT,
            NightMode.NIGHT,
        ),
    )

    /**
     * E.g.
     * cartesianProduct(listOf(1, 2, 3), listOf(true, false)) returns
     *  [(1, true), (1, false), (2, true), (2, false), (3, true), (3, false)]
     */
    fun <T, U> Iterable<T>.cartesianProduct(c2: Collection<U>): List<Pair<T, U>> {
        return flatMap { lhsElem -> c2.map { rhsElem -> lhsElem to rhsElem } }
    }
}
