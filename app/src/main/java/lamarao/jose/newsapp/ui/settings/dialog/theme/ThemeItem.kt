package lamarao.jose.newsapp.ui.settings.dialog.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import lamarao.jose.newsapp.entities.Themes
import lamarao.jose.newsapp.ui.settings.SettingsViewModel
import lamarao.jose.newsapp.ui.theme.Typography

@Composable
fun ThemeItem(
    viewModel: SettingsViewModel,
    enumTheme: Themes,
    theme: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(6.dp)
            .clickable {
                viewModel.onThemeClicked(enumTheme)
            }
    ) {
        RadioButton(
            selected = viewModel.uiState.value.selectedTheme == enumTheme.toString(),
            onClick = {
                viewModel.onThemeClicked(enumTheme)
            },
            colors = RadioButtonDefaults.colors(colors.primary)
        )
        Text(
            text = theme,
            color = colors.secondary,
            style = Typography.h6,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
