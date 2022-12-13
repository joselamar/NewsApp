package lamarao.jose.newsapp.ui.news

import lamarao.jose.newsapp.entities.Article
import lamarao.jose.newsapp.entities.CustomError
import lamarao.jose.newsapp.entities.Tabs

data class NewsUiState(
    val news: List<Article> = emptyList(),
    val newsType: String = Tabs.GENERAL.name,
    val selectedTab: Int = 0,
    val isLoading: Boolean = true,
    val error: CustomError? = null,
    val tabs: List<Tabs> = listOf(Tabs.GENERAL, Tabs.ENTERTAINMENT, Tabs.SPORTS)
)
