package lamarao.jose.newsapp.ui.settings.dialog.color

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import lamarao.jose.newsapp.entities.Colors
import lamarao.jose.newsapp.ui.settings.SettingsViewModel
import lamarao.jose.newsapp.ui.settings.dialog.CustomDialog
import lamarao.jose.newsapp.ui.theme.Blue
import lamarao.jose.newsapp.ui.theme.Green
import lamarao.jose.newsapp.ui.theme.Orange
import lamarao.jose.newsapp.ui.theme.Pink
import lamarao.jose.newsapp.ui.theme.Purple
import lamarao.jose.newsapp.ui.theme.Yellow

@Composable
fun ColorDialog(
    viewModel: SettingsViewModel
) {
    CustomDialog(text = "Choose your color") {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            ColorBox(
                viewModel = viewModel,
                enumColor = Colors.BLUE,
                color = Blue
            )
            ColorBox(
                viewModel = viewModel,
                enumColor = Colors.YELLOW,
                color = Yellow
            )
            ColorBox(
                viewModel = viewModel,
                enumColor = Colors.PINK,
                color = Pink
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            ColorBox(
                viewModel = viewModel,
                enumColor = Colors.PURPLE,
                color = Purple
            )
            ColorBox(
                viewModel = viewModel,
                enumColor = Colors.ORANGE,
                color = Orange
            )
            ColorBox(
                viewModel = viewModel,
                enumColor = Colors.GREEN,
                color = Green
            )
        }
    }
}
