package lamarao.jose.newsapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import lamarao.jose.newsapp.database.entities.Article
import lamarao.jose.newsapp.repository.NewsRepository

@HiltViewModel
class MainViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {

  init {
    viewModelScope.launch { newsRepository.refreshArticles() }
  }

  val newsResponse = newsRepository.newsResponse

  private val _navigateToNewsDetail = MutableLiveData<Article?>()
  val navigateToNewsDetail = _navigateToNewsDetail

  fun onArticleClicked(article: Article) {
    _navigateToNewsDetail.value = article
  }

  fun onArticleDetailNavigated() {
    _navigateToNewsDetail.value = null
  }
}
