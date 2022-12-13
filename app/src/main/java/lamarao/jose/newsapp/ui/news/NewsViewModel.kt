package lamarao.jose.newsapp.ui.news

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jose.newsapp.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import lamarao.jose.newsapp.common.ResultWrapper
import lamarao.jose.newsapp.entities.CustomError
import lamarao.jose.newsapp.entities.Tabs
import lamarao.jose.newsapp.repositories.datastore.DatastoreRepository
import lamarao.jose.newsapp.usecases.NewsUseCase
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
) : ViewModel() {

    private val _uiState = mutableStateOf(NewsUiState())
    val uiState: State<NewsUiState> = _uiState

    init {
        getNewsByNewsType()
    }

    fun onTabClicked(tabClicked: Int, retry: Boolean = false) {
        if (tabClicked == uiState.value.selectedTab && !retry) {
            return
        }
        _uiState.value = uiState.value.copy(
            selectedTab = tabClicked,
            isLoading = true
        )
        getNewsByNewsType(uiState.value.tabs[tabClicked].name)
    }

    private fun getNewsByNewsType(newsType: String = Tabs.GENERAL.name) {
        _uiState.value = uiState.value.copy(
            isLoading = true
        )
        viewModelScope.launch {
            newsUseCase.getNews(newsType).collectLatest { response ->
                when (response) {
                    is ResultWrapper.Error -> {
                        _uiState.value = uiState.value.copy(
                            isLoading = false,
                            error = when (response.code) {
                                TOO_MANY_REQUESTS_HTTP_CODE -> {
                                    CustomError(
                                        R.string.too_many_requests_error_title,
                                        R.string.too_many_requests_error_message
                                    )
                                }
                                else -> {
                                    CustomError(
                                        R.string.general_error_title,
                                        R.string.general_error_message
                                    )
                                }
                            }
                        )
                    }
                    is ResultWrapper.Success -> {
                        _uiState.value = uiState.value.copy(
                            news = response.data?.articles ?: emptyList(),
                            isLoading = false,
                            error = null
                        )
                    }
                }
            }
        }
    }

    companion object {
        private const val TOO_MANY_REQUESTS_HTTP_CODE = 429
    }
}
