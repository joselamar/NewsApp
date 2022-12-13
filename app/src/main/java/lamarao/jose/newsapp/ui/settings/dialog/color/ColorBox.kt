package lamarao.jose.newsapp.ui.settings.dialog.color

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import jose.newsapp.R
import lamarao.jose.newsapp.entities.Colors
import lamarao.jose.newsapp.ui.settings.SettingsViewModel

@Composable
fun ColorBox(
    viewModel: SettingsViewModel,
    enumColor: Colors,
    color: Color
) {
    Box(Modifier.width(IntrinsicSize.Max)) {
        Spacer(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(color)
                .clickable {
                    viewModel.onChangeColorClicked(enumColor)
                }
        )
        if (colors.primary == color) {
            Icon(
                painter = painterResource(id = R.drawable.ic_checkmark),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .padding(16.dp)
            )
        }
    }
}
