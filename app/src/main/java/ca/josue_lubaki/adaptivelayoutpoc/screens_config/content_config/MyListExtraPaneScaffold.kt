package ca.josue_lubaki.adaptivelayoutpoc.screens_config.content_config

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.josue_lubaki.adaptivelayoutpoc.navigation.Subjects
import ca.josue_lubaki.adaptivelayoutpoc.navigation.TAB
import ca.josue_lubaki.adaptivelayoutpoc.screens.AccessibilityScreen
import ca.josue_lubaki.adaptivelayoutpoc.screens.AndroidAutoScreen
import ca.josue_lubaki.adaptivelayoutpoc.screens.AndroidTVScreen
import ca.josue_lubaki.adaptivelayoutpoc.ui.theme.AdaptiveLayoutPocTheme

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun MyListExtraScaffold() {
    val navigator = rememberListDetailPaneScaffoldNavigator<TAB>()

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    val selectedSubject = remember {
        mutableStateOf(Subjects.ACCESSIBILITY)
    }

    SupportingPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer),
        mainPane = {
            AnimatedPane {
                MyList(
                    selectedSubject = selectedSubject.value,
                    onItemClick = { item ->
                        // Navigate to the detail pane with the passed item
                        selectedSubject.value = item.value
                        navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, item)
                    }
                )
            }
        },
        supportingPane = {
            AnimatedPane {
                // Show the detail pane content if selected item is available
                navigator.currentDestination?.content?.let { tab ->
                    MyDetails(tab)
                }
            }
        }
    )
}

@Composable
private fun MyDetails(tab: TAB) {

    val currentTab = tab.value

    // Destination content.
    when (currentTab) {
        Subjects.ACCESSIBILITY -> AccessibilityScreen()
        Subjects.ANDROID_AUTO -> AndroidAutoScreen()
        Subjects.ANDROID_TV -> AndroidTVScreen()
    }
}

@Composable
private fun MyList(
    selectedSubject: Subjects,
    onItemClick: (TAB) -> Unit
) {

    val items = Subjects.entries.map {
        TAB(it)
    }

    LazyColumn(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.primary
            )
            .padding(top = 36.dp)
            .padding(horizontal = 16.dp)
        ,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items.forEach { item ->
            item {

                // select the tab color based on the current tab.
                val selectedTabBackgroundColor : Color =
                    if (selectedSubject == item.value) {
                        MaterialTheme.colorScheme.background.copy(alpha = 0.6f)
                    } else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)

                val selectedTabColor : Color =
                    if (selectedSubject == item.value) {
                        MaterialTheme.colorScheme.onBackground
                    } else MaterialTheme.colorScheme.background

                Box(
                    Modifier
                        .fillMaxWidth()
                        .clickable {
                            onItemClick(item)
                        }
                        .background(
                            color = selectedTabBackgroundColor,
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(8.dp)
                    ,
                ) {
                    Text(
                        text = stringResource(id = item.value.label),
                        color = selectedTabColor
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun MyAppListExtraScaffoldPreview() {
    AdaptiveLayoutPocTheme {
        MyListDetailScaffold()
    }
}