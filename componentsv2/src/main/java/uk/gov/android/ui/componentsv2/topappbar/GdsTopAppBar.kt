package uk.gov.android.ui.componentsv2.topappbar

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Info
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.button.GdsIconButton
import uk.gov.android.ui.componentsv2.button.GdsIconButtonDefaults
import uk.gov.android.ui.componentsv2.button.IconButtonContent
import uk.gov.android.ui.theme.m3.GdsLocalColorScheme
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

/**
 * This is a TopAppBar that meets the GDS requirements and provides an already configured bar.
 *
 * @param modifier - modifier applied to the entire TopAppBar
 * @param title - **optional** defaults to null - GdsHeading component with a Title 2 style. Can be displayed either Start or Centre, however it it always displayed between the navigation button and actions (if existing)
 * @param navigationButton - **optional** defaults to Back icon button - the icon displayed on teh left side of the bar
 * @param onClick - defaults to a void action - action for the navigation icon button and can be left as default if no navigation icon button has been provided or is null
 * @param actions - **optional** defaults to null - Icon Buttons that will be displayed on the Start of the Bar
 * @param menu - **optional** defaults to null - allows for a DropDownMenu to be passed in and displayed - it is passed in as a Composable and managed by the consumer
 * @param alignment - defaults to Left - applies to the text only and manages if a [CenterAlignedTopAppBar] or a [TopAppBar] will be used
 * @param scrollBehaviour - **optional** defaults to null - used if a scrolled behaviour is required (see [GdsTopAppBarDefaults.scrollBehaviour] for GDS default scroll behaviour)
 * @param topAppBarColors - default to [GdsTopAppBarDefaults.colors] and can be overridden and/ or customised
 */
@OptIn(ExperimentalMaterial3Api::class, UnstableDesignSystemAPI::class)
@Composable
fun GdsTopAppBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    navigationButton: IconButtonContent? = GdsIconButtonDefaults.defaultBackContent(),
    onClick: () -> Unit = {},
    actions: ImmutableList<TopBarActionButton>? = null,
    alignment: TopAppBarAlignment = TopAppBarAlignment.Start,
    scrollBehaviour: TopAppBarScrollBehavior? = null,
    topAppBarColors: TopAppBarColors = GdsTopAppBarDefaults.colors(),
    menu: (@Composable () -> Unit)? = null,
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
                menu,
            )
        }

        TopAppBarAlignment.Centre -> {
            CenterAlignedTopAppBar(
                title = {
                    title?.let {
                        // This needs to be kept as test rather tha use the GdsHeading because the heading affects the
                        // text position within the TopAppBar - this will have the same behaviour as Heading
                        Text(
                            text = title,
                            style = Typography.headlineMedium,
                            modifier = Modifier.semantics { heading() },
                        )
                    }
                },
                navigationIcon = {
                    navigationButton?.let {
                        GdsIconButton(
                            onClick = onClick,
                            content = navigationButton,
                            color = GdsIconButtonDefaults.colors().copy(
                                contentColor = topAppBarColors.navigationIconContentColor,
                            ),
                        )
                    }
                },
                actions = {
                    ActionsAndMenu(actions, topAppBarColors, menu)
                },
                colors = topAppBarColors,
                scrollBehavior = scrollBehaviour,
                modifier = modifier,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ActionsAndMenu(
    actions: ImmutableList<TopBarActionButton>?,
    topAppBarColors: TopAppBarColors,
    menu: @Composable (() -> Unit)?,
) {
    actions?.forEach { action ->
        GdsIconButton(
            onClick = action.onClick,
            content = action.content,
            color = GdsIconButtonDefaults.colors().copy(
                contentColor = topAppBarColors.actionIconContentColor,
            ),
        )
    }
    menu?.let {
        it()
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class, UnstableDesignSystemAPI::class)
private fun StartAlignedTopAppBar(
    title: String?,
    onClick: () -> Unit,
    navigationButton: IconButtonContent?,
    topAppBarColors: TopAppBarColors,
    actions: ImmutableList<TopBarActionButton>?,
    scrollBehaviour: TopAppBarScrollBehavior?,
    modifier: Modifier = Modifier,
    menu: (@Composable () -> Unit)?,
) {
    TopAppBar(
        title = {
            title?.let {
                // This needs to be kept as test rather tha use the GdsHeading because the heading affects the
                // text position within the TopAppBar - this will have the same behaviour as Heading
                Text(
                    text = title,
                    style = Typography.headlineMedium,
                    modifier = Modifier.semantics { heading() },
                )
            }
        },
        navigationIcon = {
            navigationButton?.let {
                GdsIconButton(
                    onClick = onClick,
                    content = navigationButton,
                    color = GdsIconButtonDefaults.colors().copy(
                        contentColor = topAppBarColors.navigationIconContentColor,
                    ),
                )
            }
        },
        actions = {
            ActionsAndMenu(actions, topAppBarColors, menu)
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
        containerColor = GdsLocalColorScheme.current.topBarBackground,
        titleContentColor = GdsLocalColorScheme.current.topBarTitle,
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
    val navigationIcon: ImageVector? = null,
    @StringRes val navigationContentDescription: Int,
    val actions: ImmutableList<ActionsPreviewParams>? = null,
    val alignment: TopAppBarAlignment = TopAppBarAlignment.Start,
    val titleColor: Color? = null,
    val navigationIconColor: Color? = null,
    val actionsIconColor: Color? = null,
    val bottomDivider: Boolean = false,
)

/**
 * This is used only for previews, **DO NOT USE FOR PRODUCTION CODE**
 */
data class ActionsPreviewParams(
    val icon: ImageVector,
    @StringRes val contentDesc: Int,
)

internal class GdsTopAppBarPreviewProvider : PreviewParameterProvider<GdsTopAppBarPreviewParams> {
    override val values: Sequence<GdsTopAppBarPreviewParams> = sequenceOf(
        GdsTopAppBarPreviewParams(
            title = R.string.top_app_bar_title,
            navigationIcon = Icons.Default.Close,
            navigationContentDescription = R.string.close_icon_button,
            actions = persistentListOf(
                ActionsPreviewParams(
                    Icons.Default.MoreVert,
                    R.string.more_vert_icon_button,
                ),
            ),
        ),
        GdsTopAppBarPreviewParams(
            title = R.string.top_app_bar_title,
            navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
            navigationContentDescription = R.string.back_icon_button,
            actions = persistentListOf(
                ActionsPreviewParams(
                    Icons.Default.MoreVert,
                    R.string.more_vert_icon_button,
                ),
            ),
        ),
        GdsTopAppBarPreviewParams(
            title = R.string.top_app_bar_title,
            navigationIcon = Icons.Default.Close,
            navigationContentDescription = R.string.close_icon_button,
        ),
        GdsTopAppBarPreviewParams(
            navigationIcon = Icons.Default.Close,
            navigationContentDescription = R.string.close_icon_button,
        ),
        GdsTopAppBarPreviewParams(
            title = R.string.top_app_bar_title,
            navigationIcon = Icons.Default.Close,
            navigationContentDescription = R.string.close_icon_button,
            actions = persistentListOf(
                ActionsPreviewParams(
                    Icons.Default.MoreVert,
                    R.string.more_vert_icon_button,
                ),
            ),
            titleColor = Color.Red,
            navigationIconColor = Color.Blue,
            actionsIconColor = Color.Magenta,
        ),
        GdsTopAppBarPreviewParams(
            navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
            navigationContentDescription = R.string.back_icon_button,
            actions = persistentListOf(
                ActionsPreviewParams(
                    Icons.Default.MoreVert,
                    R.string.more_vert_icon_button,
                ),
            ),
        ),
        GdsTopAppBarPreviewParams(
            title = R.string.top_app_bar_title,
            navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
            navigationContentDescription = R.string.back_icon_button,
            actions = persistentListOf(
                ActionsPreviewParams(
                    Icons.Default.MoreVert,
                    R.string.more_vert_icon_button,
                ),
            ),
            alignment = TopAppBarAlignment.Centre,
        ),
        GdsTopAppBarPreviewParams(
            title = R.string.top_app_bar_title,
            navigationIcon = Icons.Default.Close,
            navigationContentDescription = R.string.close_icon_button,
            alignment = TopAppBarAlignment.Centre,
        ),
        GdsTopAppBarPreviewParams(
            navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
            navigationContentDescription = R.string.back_icon_button,
            actions = persistentListOf(
                ActionsPreviewParams(
                    Icons.Default.MoreVert,
                    R.string.more_vert_icon_button,
                ),
                ActionsPreviewParams(
                    Icons.Default.MoreVert,
                    R.string.more_vert_icon_button,
                ),
            ),
            alignment = TopAppBarAlignment.Centre,
        ),
        GdsTopAppBarPreviewParams(
            title = R.string.top_app_bar_title,
            navigationIcon = Icons.Default.Close,
            navigationContentDescription = R.string.close_icon_button,
            actions = persistentListOf(
                ActionsPreviewParams(
                    Icons.Default.MoreVert,
                    R.string.more_vert_icon_button,
                ),
            ),
            titleColor = Color.Red,
            navigationIconColor = Color.Blue,
            actionsIconColor = Color.Magenta,
            alignment = TopAppBarAlignment.Centre,
        ),
        GdsTopAppBarPreviewParams(
            title = R.string.top_app_bar_title,
            navigationContentDescription = R.string.close_icon_button,
            actions = persistentListOf(
                ActionsPreviewParams(
                    Icons.Outlined.Info,
                    R.string.info_icon_button,
                ),
            ),
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
        val actions: List<TopBarActionButton>? = params.actions?.map { action ->
            val contentDesc = stringResource(action.contentDesc)
            TopBarActionButton(
                content = IconButtonContent(
                    action.icon,
                    contentDesc,
                ),
                {},
            )
        }
        GdsTopAppBar(
            onClick = {},
            title = params.title?.let { stringResource(it) },
            navigationButton = params.navigationIcon?.let {
                IconButtonContent(
                    icon = params.navigationIcon,
                    contentDescription = stringResource(params.navigationContentDescription),
                )
            },
            actions = actions?.toImmutableList(),
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
