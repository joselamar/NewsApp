package lamarao.jose.newsapp.ui.newsDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import lamarao.jose.newsapp.database.entities.Article

@HiltViewModel
class NewsDetailsViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {

  val article: LiveData<Article> = savedStateHandle.getLiveData("selectedArticle")

  private val _navigateToMain = MutableLiveData<Boolean?>()
  val navigateToMain: LiveData<Boolean?> = _navigateToMain

  fun doneNavigating() {
    _navigateToMain.value = null
  }

  fun onClose() {
    _navigateToMain.value = true
  }
}
