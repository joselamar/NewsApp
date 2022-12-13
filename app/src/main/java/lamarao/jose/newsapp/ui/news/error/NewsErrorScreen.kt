package lamarao.jose.newsapp.ui.news.error

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import jose.newsapp.R
import lamarao.jose.newsapp.entities.CustomError

@Composable
fun NewsErrorScreen(
    error: CustomError,
    onTryAgainClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(vertical = 75.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            Icons.Outlined.Warning,
            "",
            modifier = Modifier
                .size(125.dp),
            tint = MaterialTheme.colors.primary
        )
        Text(
            text = stringResource(id = error.errorTitle),
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Monospace
        )
        Text(
            text = stringResource(id = error.errorMessage),
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 7.dp)
        )
        Button(onClick = { onTryAgainClicked() }) {
            Text(stringResource(id = R.string.general_error_button_action))
        }
    }
}
