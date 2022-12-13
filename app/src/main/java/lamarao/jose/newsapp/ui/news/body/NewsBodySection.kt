package lamarao.jose.newsapp.ui.news.body

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import lamarao.jose.newsapp.entities.Article
import lamarao.jose.newsapp.entities.CustomError
import lamarao.jose.newsapp.ui.news.error.NewsErrorScreen
import lamarao.jose.newsapp.ui.news.item.NewsItem
import lamarao.jose.newsapp.ui.news.item.NewsItemShimmer

@Composable
fun NewsBodySection(
    newsList: List<Article>,
    isLoading: Boolean,
    errorType: CustomError?,
    onTryAgainClicked: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
    ) {
        when {
            isLoading -> {
                items(1) {
                    Column {
                        repeat(7) {
                            NewsItemShimmer()
                        }
                    }
                }
            }
            errorType != null -> {
                items(1) {
                    NewsErrorScreen(errorType, onTryAgainClicked)
                }
            }
            else -> {
                items(newsList) { article ->
                    NewsItem(newsItem = article)
                }
            }
        }
    }
}
