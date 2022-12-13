package lamarao.jose.newsapp.ui.settings.dialog.theme

import androidx.compose.runtime.Composable
import lamarao.jose.newsapp.entities.Themes
import lamarao.jose.newsapp.ui.settings.SettingsViewModel
import lamarao.jose.newsapp.ui.settings.dialog.CustomDialog

@Composable
fun ThemeDialog(
    viewModel: SettingsViewModel
) {
    CustomDialog(text = "Choose your theme") {
        ThemeItem(viewModel = viewModel, theme = "System Default", enumTheme = Themes.DEFAULT_THEME)
        ThemeItem(viewModel = viewModel, theme = "Night Theme", enumTheme = Themes.DARK_THEME)
        ThemeItem(viewModel = viewModel, theme = "Light Theme", enumTheme = Themes.LIGHT_THEME)
    }
}
