package wskim.aos.baseuikit.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val defaultDurationMillis = 300

fun NavGraphBuilder.buildTopToBottomPage(
    route: String,
    argumentClassSimpleName: String? = null,
    pageLayout: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = route + if (!argumentClassSimpleName.isNullOrEmpty()) "/{$argumentClassSimpleName}" else "",
        arguments = if (argumentClassSimpleName != null) {
            listOf(navArgument(name = argumentClassSimpleName) {
                type = NavType.StringType
            })
        } else {
            emptyList()
        },
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Up,
                animationSpec = tween(defaultDurationMillis)
            )
        },
        popEnterTransition = null,
        exitTransition = null,
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Down,
                animationSpec = tween(defaultDurationMillis)
            )
        }
    ) {
        pageLayout.invoke(it)
    }
}

fun NavGraphBuilder.buildStartToEndPage(
    route: String,
    argumentClassSimpleName: String? = null,
    pageLayout: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = route + if (!argumentClassSimpleName.isNullOrEmpty()) "/{$argumentClassSimpleName}" else "",
        arguments = if (argumentClassSimpleName != null) {
            listOf(navArgument(name = argumentClassSimpleName) {
                type = NavType.StringType
            })
        } else {
            emptyList()
        },
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(defaultDurationMillis)
            )
        },
        popEnterTransition = null,
        exitTransition = null,
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(defaultDurationMillis)
            )
        }
    ) {
        pageLayout.invoke(it)
    }
}

fun NavGraphBuilder.buildFadeInToOutPage(
    route: String,
    argumentClassSimpleName: String? = null,
    pageLayout: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = route + if (!argumentClassSimpleName.isNullOrEmpty()) "/{$argumentClassSimpleName}" else "",
        arguments = if (argumentClassSimpleName != null) {
            listOf(navArgument(name = argumentClassSimpleName) {
                type = NavType.StringType
            })
        } else {
            emptyList()
        },
        enterTransition = {
            fadeIn(tween(durationMillis = defaultDurationMillis))
        },
        popEnterTransition = null,
        exitTransition = null,
        popExitTransition = {
            fadeOut(tween(durationMillis = defaultDurationMillis))
        }
    ) {
        pageLayout.invoke(it)
    }
}


fun NavGraphBuilder.buildTopToEndPage(
    route: String,
    argumentClassSimpleName: String? = null,
    pageLayout: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = route + if (!argumentClassSimpleName.isNullOrEmpty()) "/{$argumentClassSimpleName}" else "",
        arguments = if (argumentClassSimpleName != null) {
            listOf(navArgument(name = argumentClassSimpleName) {
                type = NavType.StringType
            })
        } else {
            emptyList()
        },
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Up,
                animationSpec = tween(defaultDurationMillis)
            )
        },
        popEnterTransition = null,
        exitTransition = null,
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(defaultDurationMillis)
            )
        }
    ) {
        pageLayout.invoke(it)
    }
}