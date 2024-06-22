package ca.josue_lubaki.adaptivelayoutpoc.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.josue_lubaki.adaptivelayoutpoc.ui.theme.AdaptiveLayoutPocTheme

@Composable
fun AccessibilityScreen() {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Accessibility Destination",
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview
@Composable
private fun AccessibilityScreenPreview() {
    AdaptiveLayoutPocTheme {
        AccessibilityScreen()
    }
}