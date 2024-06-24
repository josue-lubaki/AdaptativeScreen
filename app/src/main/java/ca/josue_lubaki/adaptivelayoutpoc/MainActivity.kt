package ca.josue_lubaki.adaptivelayoutpoc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ca.josue_lubaki.adaptivelayoutpoc.screens_config.MySuiteScaffoldLayout
import ca.josue_lubaki.adaptivelayoutpoc.ui.theme.AdaptiveLayoutPocTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdaptiveLayoutPocTheme {

                /** SuiteScaffold **/
                MySuiteScaffoldLayout()

                /** SuiteScaffold **/


            }
        }
    }
}




