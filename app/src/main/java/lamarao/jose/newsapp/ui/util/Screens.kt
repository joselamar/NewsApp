package lamarao.jose.newsapp.ui.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import jose.newsapp.R

sealed class Screens(val route: String, @DrawableRes val icon: Int, @StringRes val label: Int) {
    object MainScreen :
        Screens("main_screen", R.drawable.ic_headlines, R.string.nav_bottom_menu_entry)
    object SettingsScreen :
        Screens("settings_screen", R.drawable.ic_settings, R.string.nav_bottom_settings_entry)
}
