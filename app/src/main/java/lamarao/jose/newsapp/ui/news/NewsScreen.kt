package lamarao.jose.newsapp.ui.news

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import lamarao.jose.newsapp.ui.news.body.NewsBodySection
import lamarao.jose.newsapp.ui.news.header.NewsHeaderSection

@Composable
fun NewsScreen(
    viewModel: NewsViewModel = hiltViewModel()
) {
    Column {
        NewsHeaderSection(
            viewModel.uiState.value.selectedTab,
            onTabClick = {
                viewModel.onTabClicked(it)
            },
            viewModel.uiState.value.tabs
        )
        NewsBodySection(
            viewModel.uiState.value.news,
            viewModel.uiState.value.isLoading,
            viewModel.uiState.value.error,
            onTryAgainClicked = {
                viewModel.onTabClicked(
                    viewModel.uiState.value.selectedTab,
                    retry = true
                )
            }
        )
    }
}
