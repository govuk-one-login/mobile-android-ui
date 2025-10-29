package uk.gov.android.ui.componentsv2.camera

import androidx.camera.compose.CameraXViewfinder
import androidx.camera.core.CameraSelector
import androidx.camera.core.CameraSelector.DEFAULT_BACK_CAMERA
import androidx.camera.core.SurfaceRequest
import androidx.camera.core.UseCaseGroup
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.viewfinder.compose.MutableCoordinateTransformer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * UI for showing camera content to the User.
 *
 * Configure the [viewModel] before calling this composable function, such as for setting up Camera
 * [androidx.camera.core.UseCase] objects via [CameraContentViewModel.addAll].
 */
@Composable
fun CameraContent(
    viewModel: CameraContentViewModel,
    modifier: Modifier = Modifier,
    cameraSelector: CameraSelector = DEFAULT_BACK_CAMERA,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    coordinateTransformer: MutableCoordinateTransformer? = null,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Crop,
) {
    val surfaceRequest: SurfaceRequest? by viewModel
        .surfaceRequestFlow
        .collectAsStateWithLifecycle()
    val useCasesBuilder: UseCaseGroup.Builder by viewModel
        .useCasesBuilder
        .collectAsStateWithLifecycle()

    val context = LocalContext.current

    // Cancel the camera listener when leaving this composition.
    DisposableEffect(lifecycleOwner, useCasesBuilder) {
        val provider = ProcessCameraProvider.getInstance(context).get()

        coroutineScope.launch {
            val useCaseGroup = useCasesBuilder.build()
            provider.unbindAll()

            withContext(mainDispatcher) {
                viewModel.update(
                    provider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        useCaseGroup,
                    ),
                )
            }

            // Cancellation signals we're done with the camera
            try {
                awaitCancellation()
            } finally {
                withContext(mainDispatcher) {
                    provider.unbindAll()
                    viewModel.removeUseCases()
                }
            }
        }

        onDispose {
            provider.unbindAll()
            viewModel.removeUseCases()
        }
    }

    CameraContent(
        alignment = alignment,
        contentScale = contentScale,
        coordinateTransformer = coordinateTransformer,
        modifier = modifier,
        surfaceRequest = surfaceRequest,
    )
}

/**
 * UI for showing camera content to the User.
 *
 * Note that because this variant is immutable, it's expected that configuration of the
 * [surfaceRequest] and any analysis happens outside of this function.
 */
@Composable
fun CameraContent(
    surfaceRequest: SurfaceRequest?,
    modifier: Modifier = Modifier,
    coordinateTransformer: MutableCoordinateTransformer? = null,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Crop,
) {
    surfaceRequest?.let { request: SurfaceRequest ->
        CameraXViewfinder(
            surfaceRequest = request,
            modifier = modifier,
            coordinateTransformer = coordinateTransformer,
            alignment = alignment,
            contentScale = contentScale,
        )
    }
}
