package ca.josue_lubaki.adaptivelayoutpoc.screens_config

import android.annotation.SuppressLint
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.window.core.layout.WindowWidthSizeClass
import ca.josue_lubaki.adaptivelayoutpoc.screens.FavoritesScreen
import ca.josue_lubaki.adaptivelayoutpoc.screens.HomeScreen
import ca.josue_lubaki.adaptivelayoutpoc.screens.ProfileScreen
import ca.josue_lubaki.adaptivelayoutpoc.screens.ShoppingScreen
import ca.josue_lubaki.adaptivelayoutpoc.navigation.AppDestinations
import ca.josue_lubaki.adaptivelayoutpoc.ui.theme.AdaptiveLayoutPocTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyAppSuiteScaffold() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    // custom colors for the navigation bar items.
    val myNavigationSuiteItemColors = NavigationSuiteDefaults.itemColors(
        navigationBarItemColors = NavigationBarItemDefaults.colors(
            indicatorColor = MaterialTheme.colorScheme.primaryContainer,
            selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
    )

    // custom colors for the navigation suite, depending on the layout type.
    val navigationSuiteBarColors = NavigationSuiteDefaults.colors(
        navigationDrawerContainerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.3f),
        navigationDrawerContentColor = MaterialTheme.colorScheme.onBackground,
        navigationRailContainerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.3f),
        navigationRailContentColor = MaterialTheme.colorScheme.onBackground,
        navigationBarContainerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.3f),
        navigationBarContentColor = MaterialTheme.colorScheme.onBackground,
    )

    // choose the navigation suite type based on the window size class.
    val adaptiveInfo = currentWindowAdaptiveInfo()
    val customNavSuiteType = with(adaptiveInfo) {
        if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED) {
            NavigationSuiteType.NavigationRail
        } else {
            NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(adaptiveInfo)
        }
    }

    // Show top app bar only in compact mode.
    val showTopAppBar = with(adaptiveInfo) {
        windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT
    }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            it.icon,
                            contentDescription = stringResource(it.contentDescription),
                            tint =
                                if (it == currentDestination) MaterialTheme.colorScheme.onPrimaryContainer
                                else MaterialTheme.colorScheme.primaryContainer
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(it.label),
                            color =
                                if(it == currentDestination) MaterialTheme.colorScheme.onPrimaryContainer
                                else MaterialTheme.colorScheme.primaryContainer
                        )
                    },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it },
                    colors = myNavigationSuiteItemColors,
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        layoutType = customNavSuiteType,
        navigationSuiteColors = navigationSuiteBarColors
    ) {
        // Destination content.
        when (currentDestination) {
            AppDestinations.HOME -> HomeScreen(showTopAppBar)
            AppDestinations.FAVORITES -> FavoritesScreen()
            AppDestinations.SHOPPING -> ShoppingScreen()
            AppDestinations.PROFILE -> ProfileScreen()
            AppDestinations.INTERESTS -> MyAppListDetailScaffold()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AdaptiveLayoutPocTheme {
        MyAppSuiteScaffold()
    }
}