package uk.gov.android.ui.componentsv2.camera

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.camera.usecase.CameraUseCaseProvider.Companion.preview

@RunWith(RobolectricTestRunner::class)
class CameraContentViewModelTest {

    private val model = CameraContentViewModel()

    @Test
    fun useCasesAreStoredViaStateFlow() = runTest {
        model.addAll(
            preview(model::update).provide(),
        )
        assertEquals(
            1,
            model.useCasesBuilder.value.build().useCases.size,
        )

        model.removeUseCases()
        val exception = assertThrows(
            IllegalArgumentException::class.java,
        ) {
            model.useCasesBuilder.value.build()
        }

        assertEquals(
            "UseCase must not be empty.",
            exception.message,
        )
    }
}
