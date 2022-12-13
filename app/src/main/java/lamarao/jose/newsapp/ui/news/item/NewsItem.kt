package lamarao.jose.newsapp.ui.news.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import jose.newsapp.R
import lamarao.jose.newsapp.common.parsedDate
import lamarao.jose.newsapp.entities.Article

@Composable
fun NewsItem(
    newsItem: Article,
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 2.dp)
            .fillMaxWidth(),
        elevation = 0.dp,
        backgroundColor = colors.primaryVariant,
    ) {
        Row {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(newsItem.urlToImage)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.ic_loading_img),
                modifier = Modifier
                    .padding(6.dp)
                    .size(84.dp)
                    .align(Alignment.CenterVertically)
                    .clip(RoundedCornerShape(corner = CornerSize(8.dp))),
                error = painterResource(id = R.drawable.ic_unavailable_image),
                fallback = painterResource(id = R.drawable.ic_unavailable_image),

            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = newsItem.source.name ?: "",
                    color = colors.primary,
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Monospace
                )
                Text(
                    text = newsItem.title.dropLastWhile { c -> c != '-' }.dropLast(1),
                    color = colors.secondary,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = stringResource(
                        id = R.string.published_at,
                        newsItem.publishedAt.parsedDate()
                    ),
                    color = colors.secondary,
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}
