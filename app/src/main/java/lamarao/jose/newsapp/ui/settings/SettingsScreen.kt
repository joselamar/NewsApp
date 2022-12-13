package lamarao.jose.newsapp.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import jose.newsapp.BuildConfig
import jose.newsapp.R
import lamarao.jose.newsapp.ui.settings.dialog.color.ColorDialog
import lamarao.jose.newsapp.ui.settings.dialog.theme.ThemeDialog
import lamarao.jose.newsapp.ui.theme.Typography

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 24.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(corner = CornerSize(8.dp)))
                .clickable { viewModel.displayThemeDialog() },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.15f)
                    .padding(vertical = 20.dp)
            ) {
                Icon(painterResource(id = R.drawable.ic_theme), null, tint = colors.primary)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .padding(vertical = 20.dp)
            ) {
                Text(
                    text = "Theme",
                    color = colors.secondary,
                    style = Typography.h6,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "Choose your theme",
                    color = colors.secondary,
                    style = Typography.subtitle1
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(corner = CornerSize(8.dp)))
                .clickable {
                    viewModel.displayColorDialog()
                },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.15f)
                    .padding(vertical = 24.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(colors.primary)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .padding(vertical = 20.dp)
            ) {
                Text(
                    text = "Color",
                    color = colors.secondary,
                    style = Typography.h6,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "Choose your color",
                    color = colors.secondary,
                    style = Typography.subtitle1
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(6.dp)
                .alpha(0.5f),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Version: ${BuildConfig.VERSION_NAME}",
                color = colors.secondary,
                style = Typography.subtitle2
            )
        }

        when {
            viewModel.uiState.value.isThemeDialogVisible -> {
                Dialog(onDismissRequest = { viewModel.dismissThemeDialog() }) {
                    ThemeDialog(viewModel = viewModel)
                }
            }
            viewModel.uiState.value.isColorDialogVisible -> {
                Dialog(onDismissRequest = { viewModel.dismissColorDialog() }) {
                    ColorDialog(viewModel = viewModel)
                }
            }
        }
    }
}
