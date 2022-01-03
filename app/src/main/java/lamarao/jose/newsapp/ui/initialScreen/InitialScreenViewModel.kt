package lamarao.jose.newsapp.ui.initialScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InitialScreenViewModel : ViewModel() {

  private val _navigateToMain = MutableLiveData<Boolean?>()
  val navigateToMain: LiveData<Boolean?>
    get() = _navigateToMain

  fun doneAuthentication() {
    _navigateToMain.value = true
  }

  /** Call this immediately after navigating to [MainFragment] */
  fun doneNavigating() {
    _navigateToMain.value = null
  }

  private val _showPrompt = MutableLiveData<Boolean?>()
  val showPrompt: LiveData<Boolean?>
    get() = _showPrompt

  fun showPrompt() {
    _showPrompt.value = true
  }

  fun promptShowed() {
    _showPrompt.value = null
  }
}
