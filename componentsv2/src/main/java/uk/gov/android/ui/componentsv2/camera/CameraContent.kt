package uk.gov.android.ui.componentsv2.camera

import android.Manifest
import androidx.annotation.RequiresPermission
import androidx.camera.compose.CameraXViewfinder
import androidx.camera.core.CameraSelector.DEFAULT_FRONT_CAMERA
import androidx.camera.core.SurfaceRequest
import androidx.camera.core.UseCaseGroup
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.lifecycle.awaitInstance
import androidx.camera.viewfinder.compose.MutableCoordinateTransformer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.withContext

@Composable
@RequiresPermission(Manifest.permission.CAMERA)
fun CameraContent(
    viewModel: CameraContentViewModel,
    modifier: Modifier = Modifier,
    mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    coordinateTransformer: MutableCoordinateTransformer? = null,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Crop,
) {
    val surfaceRequest: SurfaceRequest? by viewModel
        .surfaceRequestFlow
        .collectAsStateWithLifecycle(lifecycleOwner)
    val useCasesBuilder: UseCaseGroup.Builder by viewModel
        .useCasesBuilder
        .collectAsStateWithLifecycle(lifecycleOwner)

    val context = LocalContext.current

    LaunchedEffect(useCasesBuilder, lifecycleOwner) {
        val useCaseGroup = useCasesBuilder.build()
        val provider = ProcessCameraProvider.awaitInstance(context)

        withContext(mainDispatcher) {
            viewModel.update(
                provider.bindToLifecycle(
                    lifecycleOwner,
                    DEFAULT_FRONT_CAMERA,
                    useCaseGroup,
                ),
            )
        }

        // Cancellation signals we're done with the camera
        try {
            awaitCancellation()
        } finally {
            withContext(mainDispatcher) {
                viewModel.removeUseCases()
                provider.unbindAll()
            }
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

@Composable
@RequiresPermission(Manifest.permission.CAMERA)
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
