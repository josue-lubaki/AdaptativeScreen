package ca.josue_lubaki.adaptivelayoutpoc.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ca.josue_lubaki.adaptivelayoutpoc.ui.theme.AdaptiveLayoutPocTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(showTopAppBar: Boolean = true) {
    Scaffold(
        topBar = {
            if (showTopAppBar) {
                CenterAlignedTopAppBar(title = {
                    Text(
                        text = "Adaptive Layout POC"
                    )
                },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.3f),
                        titleContentColor = MaterialTheme.colorScheme.background.copy(alpha = 0.6f),
                    )
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.primary,
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(it)
            ,
            contentAlignment = Alignment.Center
        ) {
            Text("Home Destination")
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    AdaptiveLayoutPocTheme {
        HomeScreen(showTopAppBar = true)
    }
}