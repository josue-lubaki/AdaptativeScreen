package ca.josue_lubaki.adaptivelayoutpoc.screens_config

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemColors
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuite
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldLayout
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.window.core.layout.WindowWidthSizeClass
import ca.josue_lubaki.adaptivelayoutpoc.navigation.AppDestinations
import ca.josue_lubaki.adaptivelayoutpoc.screens.FavoritesScreen
import ca.josue_lubaki.adaptivelayoutpoc.screens.HomeScreen
import ca.josue_lubaki.adaptivelayoutpoc.screens.ProfileScreen
import ca.josue_lubaki.adaptivelayoutpoc.screens.ShoppingScreen
import ca.josue_lubaki.adaptivelayoutpoc.screens_config.content_config.MyListDetailScaffold
import ca.josue_lubaki.adaptivelayoutpoc.screens_config.content_config.MyListExtraScaffold
import ca.josue_lubaki.adaptivelayoutpoc.ui.theme.AdaptiveLayoutPocTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MySuiteScaffoldLayout() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    // custom colors for the navigation bar items.
    val myNavigationSuiteItemColors = NavigationSuiteDefaults.itemColors(
        navigationBarItemColors = NavigationBarItemDefaults.colors(
            indicatorColor = MaterialTheme.colorScheme.primaryContainer,
            selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
    )

    // custom colors for the navigation rail items.
    val myNavigationSuiteRailItemColors = NavigationRailItemColors(
        unselectedIconColor = MaterialTheme.colorScheme.primary,
        selectedIconColor = MaterialTheme.colorScheme.primary,
        unselectedTextColor = MaterialTheme.colorScheme.onBackground,
        selectedTextColor = MaterialTheme.colorScheme.onBackground,
        disabledIconColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f),
        disabledTextColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f),
        selectedIndicatorColor = MaterialTheme.colorScheme.primaryContainer,
    )

    // custom colors for the navigation items.
    // custom colors for the navigation suite, depending on the layout type.
    val navigationSuiteBarColors = NavigationSuiteDefaults.colors(
        navigationDrawerContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
        navigationDrawerContentColor = MaterialTheme.colorScheme.onPrimary,
        navigationRailContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
        navigationRailContentColor = MaterialTheme.colorScheme.onPrimary,
        navigationBarContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
        navigationBarContentColor = MaterialTheme.colorScheme.onPrimary,
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

    NavigationSuiteScaffoldLayout(
        layoutType = customNavSuiteType,
        navigationSuite = @Composable {
            // Custom Navigation Rail with centered items.
            if (customNavSuiteType == NavigationSuiteType.NavigationRail) {
                NavigationRail(
                    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                ) {
                    // Adding Spacers before and after the item so they are pushed towards the
                    // center of the NavigationRail.
                    Spacer(Modifier.weight(1f))
                    AppDestinations.entries.forEach { item ->
                        NavigationRailItem(
                            icon = {
                                Icon(
                                    item.icon,
                                    contentDescription = stringResource(item.contentDescription),
                                    tint =
                                        if (item == currentDestination) MaterialTheme.colorScheme.onPrimaryContainer
                                        else MaterialTheme.colorScheme.primaryContainer
                                )
                            },
                            label = {
                                Text(
                                    text = stringResource(item.label),
                                    color =
                                    if(item == currentDestination) MaterialTheme.colorScheme.onPrimaryContainer
                                    else MaterialTheme.colorScheme.primaryContainer
                                )
                            },
                            selected = item == currentDestination,
                            onClick = { currentDestination = item },
                            colors = myNavigationSuiteRailItemColors,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(Modifier.weight(1f))
                }
            } else {
                NavigationSuite(
                    colors = navigationSuiteBarColors,
                ) {
                    AppDestinations.entries.forEach {
                        item(
                            icon = {
                                Icon(
                                    it.icon,
                                    contentDescription = stringResource(it.contentDescription)
                                )
                            },
                            label = { Text(stringResource(it.label)) },
                            selected = it == currentDestination,
                            onClick = { currentDestination = it },
                            colors = myNavigationSuiteItemColors,
                        )
                    }
                }
            }
        }
    ) {
        // Destination content.
        when (currentDestination) {
            AppDestinations.HOME -> HomeScreen(showTopAppBar)
            AppDestinations.FAVORITES -> FavoritesScreen()
            AppDestinations.SHOPPING -> ShoppingScreen()
            AppDestinations.PROFILE -> ProfileScreen()

            /*** use the list & detail scaffold for the interests destination. ***/
            AppDestinations.INTERESTS -> MyListDetailScaffold()

            /*** use the extra scaffold for the interests destination. ***/
//            AppDestinations.INTERESTS -> MyListExtraScaffold()
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun MyAppRailPreview() {
    AdaptiveLayoutPocTheme {
        MySuiteScaffoldLayout()
    }
}