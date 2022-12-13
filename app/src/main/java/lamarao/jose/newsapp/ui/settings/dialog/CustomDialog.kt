package lamarao.jose.newsapp.ui.settings.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import lamarao.jose.newsapp.ui.theme.Typography

@Composable
fun CustomDialog(
    text: String,
    dialogContent: @Composable () -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth(),
        border = BorderStroke(1.dp, color = MaterialTheme.colors.secondary),
        elevation = 8.dp
    ) {
        Column(
            Modifier
                .background(MaterialTheme.colors.background)
        ) {
            Text(
                text = text,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 12.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                style = Typography.h5
            )
            dialogContent()
        }
    }
}
