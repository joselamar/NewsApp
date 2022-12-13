package lamarao.jose.newsapp.ui.settings

data class SettingsUiState(
    val selectedTheme: String,
    val isColorDialogVisible: Boolean = false,
    val isThemeDialogVisible: Boolean = false
)
