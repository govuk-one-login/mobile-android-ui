package uk.gov.android.ui.componentsv2.progress

import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GdsProgressIndicatorStateTest {

    companion object {
        val fiveSeconds = 5.seconds
    }

    @Test
    fun `given default initial wait time, it starts from Short wait`() = runTest {
        val state = GdsProgressIndicatorState()

        assertEquals(ProgressWaitLength.Short, state.waitedFor)
    }

    @Test
    fun `given initial Long wait, it starts from Long wait`() = runTest {
        val state = GdsProgressIndicatorState(
            initialWaitedFor = ProgressWaitLength.Long,
        )

        assertEquals(ProgressWaitLength.Long, state.waitedFor)
    }

    @Test
    fun `given initial Longer wait, it starts from Longer wait`() = runTest {
        val state = GdsProgressIndicatorState(
            initialWaitedFor = ProgressWaitLength.Longer,
        )

        assertEquals(ProgressWaitLength.Longer, state.waitedFor)
    }

    @Test
    fun `when less than 5 seconds elapsed, it stays on Short wait`() = runTest {
        val state = GdsProgressIndicatorState()
        backgroundScope.launch { state.runAnimation() }

        advanceTimeBy(fiveSeconds - 1.milliseconds)
        runCurrent()

        assertEquals(ProgressWaitLength.Short, state.waitedFor)
    }

    @Test
    fun `when full duration elapsed it transitions through all states`() = runTest {
        val state = GdsProgressIndicatorState()
        backgroundScope.launch { state.runAnimation() }

        runCurrent()
        assertEquals(ProgressWaitLength.Short, state.waitedFor)

        advanceTimeBy(fiveSeconds)
        runCurrent()

        assertEquals(ProgressWaitLength.Long, state.waitedFor)

        advanceTimeBy(fiveSeconds)
        runCurrent()

        assertEquals(ProgressWaitLength.Longer, state.waitedFor)
    }

    @Test
    fun `given it starts at a Long wait, when less than 5 seconds elapsed, it stays on Long wait`() =
        runTest {
            val state = GdsProgressIndicatorState(
                initialWaitedFor = ProgressWaitLength.Long,
            )
            backgroundScope.launch { state.runAnimation() }

            advanceTimeBy(fiveSeconds - 1.milliseconds)
            runCurrent()

            assertEquals(ProgressWaitLength.Long, state.waitedFor)
        }

    @Test
    fun `given it starts at a Long wait, when 5 seconds elapsed, it transitions to Longer wait`() =
        runTest {
            val state = GdsProgressIndicatorState(
                initialWaitedFor = ProgressWaitLength.Long,
            )
            backgroundScope.launch { state.runAnimation() }

            runCurrent()
            assertEquals(ProgressWaitLength.Long, state.waitedFor)

            advanceTimeBy(fiveSeconds)
            runCurrent()

            assertEquals(ProgressWaitLength.Longer, state.waitedFor)
        }

    @Test
    fun `given it starts at a Longer wait, when a long time elapsed, it doesn't transition`() =
        runTest {
            val state = GdsProgressIndicatorState(
                initialWaitedFor = ProgressWaitLength.Longer,
            )
            backgroundScope.launch { state.runAnimation() }

            advanceTimeBy(Long.MAX_VALUE.days)
            runCurrent()

            assertEquals(ProgressWaitLength.Longer, state.waitedFor)
        }
}
