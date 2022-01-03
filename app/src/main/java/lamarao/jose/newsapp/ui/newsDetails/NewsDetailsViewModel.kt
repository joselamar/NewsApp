package lamarao.jose.newsapp.ui.newsDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lamarao.jose.newsapp.database.Article

class NewsDetailsViewModel(art: Article) : ViewModel() {

  private val _article = MutableLiveData<Article>()
  val article: LiveData<Article>
    get() = _article

  init {
    _article.value = art
  }

  private val _navigateToMain = MutableLiveData<Boolean?>()
  val navigateToMain: LiveData<Boolean?>
    get() = _navigateToMain

  /** Call this immediately after navigating to [MainFragment] */
  fun doneNavigating() {
    _navigateToMain.value = null
  }

  fun onClose() {
    _navigateToMain.value = true
  }
}
