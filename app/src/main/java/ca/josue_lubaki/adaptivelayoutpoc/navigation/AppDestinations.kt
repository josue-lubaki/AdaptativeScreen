package ca.josue_lubaki.adaptivelayoutpoc.navigation

import android.os.Parcelable
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import ca.josue_lubaki.adaptivelayoutpoc.R
import kotlinx.parcelize.Parcelize

enum class AppDestinations(
    @StringRes val label: Int,
    val icon: ImageVector,
    @StringRes val contentDescription: Int
) {
    HOME(
        label = R.string.home,
        icon = Icons.Default.Home,
        contentDescription = R.string.home
    ),
    FAVORITES(
        label = R.string.favorites,
        icon = Icons.Default.Favorite,
        contentDescription = R.string.favorites
    ),
    SHOPPING(
        label = R.string.shopping,
        icon = Icons.Default.ShoppingCart,
        contentDescription = R.string.shopping
    ),
    PROFILE(
        label = R.string.profile,
        icon = Icons.Default.AccountBox,
        contentDescription = R.string.profile
    ),
    INTERESTS(
        label = R.string.interests,
        icon = Icons.Default.Favorite,
        contentDescription = R.string.interests
    ),
}

enum class Subjects(@StringRes val label: Int) {
    ACCESSIBILITY(label = R.string.accessibility),
    ANDROID_AUTO(label = R.string.android_auto),
    ANDROID_TV(label = R.string.android_tv),
}

@Parcelize
class TAB(val value: Subjects) : Parcelable