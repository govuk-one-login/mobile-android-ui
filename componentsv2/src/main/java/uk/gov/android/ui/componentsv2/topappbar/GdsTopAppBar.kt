package uk.gov.android.ui.componentsv2.topappbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.button.GdsIconButton
import uk.gov.android.ui.componentsv2.button.GdsIconButtonDefaults
import uk.gov.android.ui.componentsv2.button.IconButtonContent
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.heading.GdsHeadingStyle
import uk.gov.android.ui.theme.m3.GdsLocalColorScheme
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

/**
 * This is a TopAppBar that meets the GDS requirements and provides an already configured bar.
 *
 * @param onClick - required - action for the navigation icon button
 * @param modifier - modifier applied to the entire TopAppBar
 * @param title - **optional** defaults to null - GdsHeading component with a Title 2 style. Can be displayed either Start or Centre, however it it always displayed between the navigation button and actions (if existing)
 * @param navigationButton - defaults to Back icon button - the icon displayed on teh left side of the bar (always displayed)
 * @param actions - **optional** defaults to null - Icon Buttons that will be displayed on the Start of the Bar
 * @param alignment - defaults to Left - applies to the text only and manages if a [CenterAlignedTopAppBar] or a [TopAppBar] will be used
 * @param scrollBehaviour - **optional** defaults to null - used if a scrolled behaviour is required (see [GdsTopAppBarDefaults.scrollBehaviour] for GDS default scroll behaviour)
 * @param topAppBarColors - default to [GdsTopAppBarDefaults.colors] and can be overridden and/ or customised
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GdsTopAppBar(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    navigationButton: IconButtonContent = GdsIconButtonDefaults.defaultBackContent,
    actions: ImmutableList<TopBarActionButton>? = null,
    alignment: TopAppBarAlignment = TopAppBarAlignment.Start,
    scrollBehaviour: TopAppBarScrollBehavior? = null,
    topAppBarColors: TopAppBarColors = GdsTopAppBarDefaults.colors(),
) {
    when (alignment) {
        TopAppBarAlignment.Start -> {
            StartAlignedTopAppBar(
                title,
                onClick,
                navigationButton,
                topAppBarColors,
                actions,
                scrollBehaviour,
                modifier,
            )
        }

        TopAppBarAlignment.Centre -> {
            CenterAlignedTopAppBar(
                title = {
                    title?.let {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.headlineMedium,
                        )
                    }
                },
                navigationIcon = {
                    GdsIconButton(
                        onClick = onClick,
                        content = navigationButton,
                        color = GdsIconButtonDefaults.colors().copy(
                            contentColor = topAppBarColors.navigationIconContentColor,
                        ),
                    )
                },
                actions = {
                    actions?.forEach { action ->
                        GdsIconButton(
                            onClick = action.onClick,
                            content = action.content,
                            color = GdsIconButtonDefaults.colors().copy(
                                contentColor = topAppBarColors.actionIconContentColor,
                            ),
                        )
                    }
                },
                colors = topAppBarColors,
                scrollBehavior = scrollBehaviour,
                modifier = modifier,
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class, UnstableDesignSystemAPI::class)
private fun StartAlignedTopAppBar(
    title: String?,
    onClick: () -> Unit,
    navigationButton: IconButtonContent,
    topAppBarColors: TopAppBarColors,
    actions: ImmutableList<TopBarActionButton>?,
    scrollBehaviour: TopAppBarScrollBehavior?,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            title?.let {
                GdsHeading(
                    text = title,
                    style = GdsHeadingStyle.Title2,
                )
            }
        },
        navigationIcon = {
            GdsIconButton(
                onClick = onClick,
                content = navigationButton,
                color = GdsIconButtonDefaults.colors().copy(
                    contentColor = topAppBarColors.navigationIconContentColor,
                ),
            )
        },
        actions = {
            actions?.forEach { action ->
                GdsIconButton(
                    onClick = action.onClick,
                    content = action.content,
                    color = GdsIconButtonDefaults.colors().copy(
                        contentColor = topAppBarColors.actionIconContentColor,
                    ),
                )
            }
        },
        colors = topAppBarColors,
        scrollBehavior = scrollBehaviour,
        modifier = modifier,
    )
}

/**
 * Class to define GDS TopBarActions to allow for multiple items to be passed in, as required.
 * @param content - Icon as [ImageVector] and it's contentDescription
 * @param onClick = action when the Icon Button is tapped
 */
data class TopBarActionButton(
    val content: IconButtonContent,
    val onClick: () -> Unit,
)

enum class TopAppBarAlignment {
    Start, Centre
}

/**
 * Object that allows for GDS specific TOP app Bar defaults to be defined and ready to use.
 */
object GdsTopAppBarDefaults {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun colors() = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.background,
        titleContentColor = MaterialTheme.colorScheme.onBackground,
        navigationIconContentColor = GdsLocalColorScheme.current.topBarIcon,
        actionIconContentColor = GdsLocalColorScheme.current.topBarIcon,
        scrolledContainerColor = GdsLocalColorScheme.current
            .topBarScrolledBackground,
    )

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun scrollBehaviour() = TopAppBarDefaults.pinnedScrollBehavior()
}

data class GdsTopAppBarPreviewParams(
    val title: Int? = null,
    val navigationIcon: IconButtonContent,
    val actions: ImmutableList<TopBarActionButton>? = null,
    val alignment: TopAppBarAlignment = TopAppBarAlignment.Start,
    val titleColor: Color? = null,
    val navigationIconColor: Color? = null,
    val actionsIconColor: Color? = null,
)

internal class GdsTopAppBarPreviewProvider : PreviewParameterProvider<GdsTopAppBarPreviewParams> {
    override val values: Sequence<GdsTopAppBarPreviewParams> = sequenceOf(
        GdsTopAppBarPreviewParams(
            title = R.string.top_app_bar_title,
            navigationIcon = GdsIconButtonDefaults.defaultCloseContent,
            actions = persistentListOf(
                TopBarActionButton(
                    content = IconButtonContent(
                        Icons.Default.MoreVert,
                        R.string.more_vert_icon_button,
                    ),
                    {},
                ),
            ),
        ),
        GdsTopAppBarPreviewParams(
            title = R.string.top_app_bar_title,
            navigationIcon = GdsIconButtonDefaults.defaultBackContent,
            actions = persistentListOf(
                TopBarActionButton(
                    content = IconButtonContent(
                        Icons.Default.MoreVert,
                        R.string.more_vert_icon_button,
                    ),
                    {},
                ),
            ),
        ),
        GdsTopAppBarPreviewParams(
            title = R.string.top_app_bar_title,
            navigationIcon = GdsIconButtonDefaults.defaultCloseContent,
        ),
        GdsTopAppBarPreviewParams(
            navigationIcon = GdsIconButtonDefaults.defaultCloseContent,
        ),
        GdsTopAppBarPreviewParams(
            title = R.string.top_app_bar_title,
            navigationIcon = GdsIconButtonDefaults.defaultCloseContent,
            actions = persistentListOf(
                TopBarActionButton(
                    content = IconButtonContent(
                        Icons.Default.MoreVert,
                        R.string.more_vert_icon_button,
                    ),
                    {},
                ),
            ),
            titleColor = Color.Red,
            navigationIconColor = Color.Blue,
            actionsIconColor = Color.Magenta,
        ),
        GdsTopAppBarPreviewParams(
            navigationIcon = GdsIconButtonDefaults.defaultBackContent,
            actions = persistentListOf(
                TopBarActionButton(
                    content = IconButtonContent(
                        Icons.Default.MoreVert,
                        R.string.more_vert_icon_button,
                    ),
                    {},
                ),
            ),
        ),
        GdsTopAppBarPreviewParams(
            title = R.string.top_app_bar_title,
            navigationIcon = GdsIconButtonDefaults.defaultBackContent,
            actions = persistentListOf(
                TopBarActionButton(
                    content = IconButtonContent(
                        Icons.Default.MoreVert,
                        R.string.more_vert_icon_button,
                    ),
                    {},
                ),
            ),
            alignment = TopAppBarAlignment.Centre,
        ),
        GdsTopAppBarPreviewParams(
            title = R.string.top_app_bar_title,
            navigationIcon = GdsIconButtonDefaults.defaultCloseContent,
            alignment = TopAppBarAlignment.Centre,
        ),
        GdsTopAppBarPreviewParams(
            navigationIcon = GdsIconButtonDefaults.defaultBackContent,
            actions = persistentListOf(
                TopBarActionButton(
                    content = IconButtonContent(
                        Icons.Default.MoreVert,
                        R.string.more_vert_icon_button,
                    ),
                    {},
                ),
            ),
            alignment = TopAppBarAlignment.Centre,
        ),
        GdsTopAppBarPreviewParams(
            title = R.string.top_app_bar_title,
            navigationIcon = GdsIconButtonDefaults.defaultCloseContent,
            actions = persistentListOf(
                TopBarActionButton(
                    content = IconButtonContent(
                        Icons.Default.MoreVert,
                        R.string.more_vert_icon_button,
                    ),
                    {},
                ),
            ),
            titleColor = Color.Red,
            navigationIconColor = Color.Blue,
            actionsIconColor = Color.Magenta,
            alignment = TopAppBarAlignment.Centre,
        ),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewLightDark
@Composable
internal fun GdsTopAppBarPreview(
    @PreviewParameter(GdsTopAppBarPreviewProvider::class)
    params: GdsTopAppBarPreviewParams,
) {
    GdsTheme {
        GdsTopAppBar(
            onClick = {},
            title = params.title?.let { stringResource(it) },
            navigationButton = params.navigationIcon,
            actions = params.actions,
            topAppBarColors = GdsTopAppBarDefaults.colors().copy(
                titleContentColor = params.titleColor ?: MaterialTheme.colorScheme.onBackground,
                navigationIconContentColor =
                params.navigationIconColor ?: GdsLocalColorScheme.current.topBarIcon,
                actionIconContentColor =
                params.actionsIconColor ?: GdsLocalColorScheme.current.topBarIcon,
            ),
            alignment = params.alignment,
        )
    }
}
